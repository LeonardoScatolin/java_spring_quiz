package br.com.leo.java_spring_quiz.modules.students.useCases;

import br.com.leo.java_spring_quiz.modules.questions.entities.QuestionEntity;
import br.com.leo.java_spring_quiz.modules.questions.repositories.QuestionRepository;
import br.com.leo.java_spring_quiz.modules.students.dto.StudentCertificationAnswerDTO;
import br.com.leo.java_spring_quiz.modules.students.dto.VerifyCertificationDTO;
import br.com.leo.java_spring_quiz.modules.students.entities.AnswersCertificationEntity;
import br.com.leo.java_spring_quiz.modules.students.entities.CertificationStudentEntity;
import br.com.leo.java_spring_quiz.modules.students.entities.StudentEntity;
import br.com.leo.java_spring_quiz.modules.students.repositories.CertificationStudentRepository;
import br.com.leo.java_spring_quiz.modules.students.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StudentCertificationAnswersUseCase {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    @Autowired
    private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

    public CertificationStudentEntity execute(StudentCertificationAnswerDTO dto) throws Exception {

        var hasCertification = this.verifyIfHasCertificationUseCase.execute(new VerifyCertificationDTO(dto.getEmail(), dto.getTechnology()));

        if (hasCertification) {
            throw new Exception("Você já tirou sua certificação!");
        }

        List<QuestionEntity> questionsEntity = questionRepository.findByTechnology(dto.getTechnology());
        List<AnswersCertificationEntity> answersCertification = new ArrayList<>();

        AtomicInteger correctAnswers = new AtomicInteger(0);

        dto.getQuestionsAnswers().stream().forEach(questionAnswer -> {
            var question = questionsEntity.stream()
                    .filter(q -> q.getId().equals(questionAnswer.getQuestionID())).findFirst().get();

            var findCorrectAlternative = question.getAlternativesEntities().stream()
                    .filter(alternative -> alternative.isCorrect()).findFirst().get();

            if (findCorrectAlternative.getId().equals(questionAnswer.getAlternativeID())) {
                questionAnswer.setCorrect(true);
                correctAnswers.incrementAndGet();
            } else {
                questionAnswer.setCorrect(false);
            }

            var answersCertificationsEntity = AnswersCertificationEntity.builder().answerID(questionAnswer.getAlternativeID()).questionID(questionAnswer.getQuestionID()).isCorrect(questionAnswer.isCorrect()).build();

            answersCertification.add(answersCertificationsEntity);

        });

        var student = studentRepository.findByEmail(dto.getEmail());
        UUID studentID;
        if (student.isEmpty()) {
            var studentCreated = StudentEntity.builder().email(dto.getEmail()).build();
            studentCreated = studentRepository.save(studentCreated);
            studentID = studentCreated.getId();
        } else {
            studentID = student.get().getId();
        }



        CertificationStudentEntity certificationStudentEntity = CertificationStudentEntity.builder()
                .technology(dto.getTechnology())
                .studentID(studentID)
                .grade(correctAnswers.get())
                .build();

        var certificationStudentCreated = certificationStudentRepository.save(certificationStudentEntity);

        answersCertification.stream().forEach(answerCertification -> {
            answerCertification.setCertificationID(certificationStudentEntity.getId());
            answerCertification.setCertificationStudentEntity(certificationStudentEntity);
        });

        certificationStudentEntity.setAnswersCertificationEntities(answersCertification);

        certificationStudentRepository.save(certificationStudentEntity);

        return certificationStudentCreated;
    }

}
