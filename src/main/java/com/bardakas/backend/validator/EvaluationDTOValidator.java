package com.bardakas.backend.validator;

import com.bardakas.backend.entity.dto.EvaluationDTO;
import org.springframework.stereotype.Component;

@Component
public class EvaluationDTOValidator {

    private final MandatoryStringValueValidator mandatoryStringValueValidator;
    private final CommentsValidator commentsValidator;

    public EvaluationDTOValidator(MandatoryStringValueValidator mandatoryStringValueValidator,
                                  CommentsValidator commentsValidator){
        this.mandatoryStringValueValidator = mandatoryStringValueValidator;
        this.commentsValidator = commentsValidator;
    }

    public void validate(EvaluationDTO evaluationDTO) {
        commentsValidator.validate(evaluationDTO.getTeacherComment(), "Teacher comments cannot exceed 255 characters");
        commentsValidator.validate(evaluationDTO.getCommunicationComment(), "Communication comments cannot exceed 255 characters");
        commentsValidator.validate(evaluationDTO.getExtraMileComments(), "Extra Mile comments cannot exceed 255 characters");
        commentsValidator.validate(evaluationDTO.getMotivationComments(), "Motivation comments cannot exceed 255 characters");
        mandatoryStringValueValidator.validate(evaluationDTO.getDirectionComments(), "Direction comments cannot be blank");
        commentsValidator.validate(evaluationDTO.getDirectionComments(),"Direction comments cannot exceed 255 characters" );
        commentsValidator.validate(evaluationDTO.getAbilityToLearnComments(),"Ability to learn comments cannot exceed 255 characters" );
        commentsValidator.validate(evaluationDTO.getOverallComments(), "Overall evaluationDTO comments cannot exceed 255 characters");
        mandatoryStringValueValidator.validate(evaluationDTO.getOverallComments(), "Overall evaluationDTO comments cannot be blank");
    }
}
