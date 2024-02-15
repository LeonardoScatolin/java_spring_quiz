package br.com.leo.java_spring_quiz.modules.students.controllers;

import br.com.leo.java_spring_quiz.modules.students.dto.StudentCertificationAnswerDTO;
import br.com.leo.java_spring_quiz.modules.students.dto.VerifyCertificationDTO;
import br.com.leo.java_spring_quiz.modules.students.entities.CertificationStudentEntity;
import br.com.leo.java_spring_quiz.modules.students.useCases.StudentCertificationAnswersUseCase;
import br.com.leo.java_spring_quiz.modules.students.useCases.VerifyIfHasCertificationUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

    @Autowired
    private StudentCertificationAnswersUseCase studentCertificationAnswersUseCase;

    @PostMapping("/verifyIfHasCertification")
    public String verifyIfHasCertification(@RequestBody VerifyCertificationDTO verifyCertificationDTO) {
        // Email
        // Technology
        var result = this.verifyIfHasCertificationUseCase.execute(verifyCertificationDTO);
        if (result) {
            return "Usuário já fez a prova";
        }

        return "Usuário pode fazer a prova";
    }

    @PostMapping("/certification/answer")
    public ResponseEntity<Object> certificationAnswer(
            @RequestBody StudentCertificationAnswerDTO studentCertificationAnswerDTO) {
        try {
            var result = studentCertificationAnswersUseCase.execute(studentCertificationAnswerDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
