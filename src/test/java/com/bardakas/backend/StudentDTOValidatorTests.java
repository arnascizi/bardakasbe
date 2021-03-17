package com.bardakas.backend;

import com.bardakas.backend.entity.db.Student;
import com.bardakas.backend.entity.dto.StudentDTO;
import com.bardakas.backend.exception.ValidationException;
import com.bardakas.backend.validator.StudentDTOValidator;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StudentDTOValidatorTests {

    @Autowired
    private StudentDTOValidator studentDTOValidator;


    private final String testStringOver64Characters = RandomStringUtils.randomAlphabetic(65);
    private final String testStringBlank = " ";
    private final String testStringUnder64CharactersAndNotBlank = RandomStringUtils.randomAlphabetic(15);


    @Test
    public void validate_StudentNameOver64Characters_ExceptionThrown() {
        Assertions.assertThrows(ValidationException.class, () -> {
            StudentDTO testStudentDTO = new StudentDTO();
            testStudentDTO.setName(testStringOver64Characters);
            testStudentDTO.setSurname(testStringUnder64CharactersAndNotBlank);
            studentDTOValidator.validate(testStudentDTO);
        });
    }

    @Test
    public void validate_StudentNameBlank_ExceptionThrown() {
        Assertions.assertThrows(ValidationException.class, () -> {
            StudentDTO testStudentDTO = new StudentDTO();
            testStudentDTO.setName(testStringBlank);
            testStudentDTO.setSurname(testStringUnder64CharactersAndNotBlank);
            studentDTOValidator.validate(testStudentDTO);
        });
    }

    @Test
    public void validate_StudentSurnameOver64Characters_ExceptionThrown() {
        Assertions.assertThrows(ValidationException.class, () -> {
            StudentDTO testStudentDTO = new StudentDTO();
            testStudentDTO.setName(testStringUnder64CharactersAndNotBlank);
            testStudentDTO.setSurname(testStringOver64Characters);
            studentDTOValidator.validate(testStudentDTO);
        });
    }

    @Test
    public void validate_StudentSurnameBlank_ExceptionThrown() {
        Assertions.assertThrows(ValidationException.class, () -> {
            StudentDTO testStudentDTO = new StudentDTO();
            testStudentDTO.setName(testStringUnder64CharactersAndNotBlank);
            testStudentDTO.setSurname(testStringBlank);
            studentDTOValidator.validate(testStudentDTO);
        });
    }

    @Test
    public void validate_StudentNameAndSurnameUnder64CharactersAndNotBlank_ExceptionNotThrown() {
        Assertions.assertDoesNotThrow(() -> {
            StudentDTO testStudentDTO = new StudentDTO();
            testStudentDTO.setName(testStringUnder64CharactersAndNotBlank);
            testStudentDTO.setSurname(testStringUnder64CharactersAndNotBlank);
            studentDTOValidator.validate(testStudentDTO);
        });
    }


}
