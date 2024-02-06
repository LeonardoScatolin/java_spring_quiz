package br.com.leo.java_spring_quiz.modules.students.useCases;

import br.com.leo.java_spring_quiz.modules.students.dto.VerifyCertificationDTO;
import org.springframework.stereotype.Service;

@Service
public class VerifyIfHasCertificationUseCase {

    public boolean execute(VerifyCertificationDTO dto) {
        if (dto.getEmail().equals("leo@gmail.com") && dto.getTechnology().equals("Java")) {
            return true;
        }
        return false;
    }

}
