package br.com.leo.java_spring_quiz.modules.questions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlternativesResultDTO {

    private UUID id;
    private String description;

}
