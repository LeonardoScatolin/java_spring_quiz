package br.com.leo.java_spring_quiz.modules.certifications.useCases;

import br.com.leo.java_spring_quiz.modules.students.entities.CertificationStudentEntity;
import br.com.leo.java_spring_quiz.modules.students.repositories.CertificationStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Top10RankingUseCase {

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    public List<CertificationStudentEntity> execute() {
        return this.certificationStudentRepository.findTop10ByOrderByGradeDesc();
    }
}
