package br.com.leo.java_spring_quiz.modules.students.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyCertificationDTO {

    private String email;
    private String technology;

}
