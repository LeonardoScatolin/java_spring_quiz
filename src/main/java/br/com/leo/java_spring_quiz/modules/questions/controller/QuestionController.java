package br.com.leo.java_spring_quiz.modules.questions.controller;

import br.com.leo.java_spring_quiz.modules.questions.dto.AlternativesResultDTO;
import br.com.leo.java_spring_quiz.modules.questions.dto.QuestionResultDTO;
import br.com.leo.java_spring_quiz.modules.questions.entities.AlternativesEntity;
import br.com.leo.java_spring_quiz.modules.questions.entities.QuestionEntity;
import br.com.leo.java_spring_quiz.modules.questions.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/technology/{technology}")
    public List<QuestionResultDTO> findByTechnology(@PathVariable String technology) {
        var result = this.questionRepository.findByTechnology(technology);

        var toMap = result.stream().map(question -> mapQuestionToDTO(question)).collect(Collectors.toList());
        return toMap;
    }

    static QuestionResultDTO mapQuestionToDTO(QuestionEntity question) {
        var questionResultDTO = QuestionResultDTO.builder().id(question.getId()).technology(question.getTechnology()).description(question.getDescription()).build();

        List<AlternativesResultDTO> alternatives = question.getAlternativesEntities().stream().map(alternative -> mapAlternativeDTO(alternative)).toList();

        questionResultDTO.setAlternatives(alternatives);
        return questionResultDTO;
    }

    static AlternativesResultDTO mapAlternativeDTO(AlternativesEntity alternativesEntity) {
        return AlternativesResultDTO.builder().id(alternativesEntity.getId()).description(alternativesEntity.getDescription()).build();
    }

}
