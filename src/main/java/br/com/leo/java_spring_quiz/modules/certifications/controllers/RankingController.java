package br.com.leo.java_spring_quiz.modules.certifications.controllers;

import br.com.leo.java_spring_quiz.modules.certifications.useCases.Top10RankingUseCase;
import br.com.leo.java_spring_quiz.modules.students.entities.CertificationStudentEntity;
import br.com.leo.java_spring_quiz.modules.students.repositories.CertificationStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    @Autowired
    private Top10RankingUseCase top10RankingUseCase;

    @GetMapping("/top10")
    public List<CertificationStudentEntity> top10() {
        return this.top10RankingUseCase.execute();
    }

}
