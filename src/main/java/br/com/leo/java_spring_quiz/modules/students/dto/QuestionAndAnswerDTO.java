package br.com.leo.java_spring_quiz.modules.students.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAndAnswerDTO {

    private UUID questionID;
    private UUID alternativeID;
    private boolean isCorrect;

}
