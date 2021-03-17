package com.bardakas.backend.validator;

import com.bardakas.backend.entity.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentDTOValidator {

    private MandatoryStringValueValidator mandatoryStringValueValidator;
    private NameAndSurnameLengthValidator nameAndSurnameLengthValidator;

    @Autowired
    public StudentDTOValidator(MandatoryStringValueValidator mandatoryStringValueValidator, NameAndSurnameLengthValidator nameAndSurnameLengthValidator) {
        this.mandatoryStringValueValidator = mandatoryStringValueValidator;
        this.nameAndSurnameLengthValidator = nameAndSurnameLengthValidator;
    }

    public void validate(StudentDTO studentDTO) {
        mandatoryStringValueValidator.validate(studentDTO.getName(), "Student name cannot be blank");
        nameAndSurnameLengthValidator.validate(studentDTO.getName(), "Student name cannot exceed 64 characters");
        mandatoryStringValueValidator.validate(studentDTO.getSurname(), "Student surname cannot be blank");
        nameAndSurnameLengthValidator.validate(studentDTO.getSurname(), "Student surname cannot exceed 64 characters");
    }
}
