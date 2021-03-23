package com.bardakas.backend;

import com.bardakas.backend.entity.Teacher;
import com.bardakas.backend.exception.ValidationException;
import com.bardakas.backend.validator.TeacherValidator;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TeacherValidatorTest {

    @Autowired
    private TeacherValidator teacherValidator;

    private final String textBlank = " ";
    private final String textOver64Characters = RandomStringUtils.randomAlphabetic(65);
    private final String textUnder60Characters = RandomStringUtils.randomAlphabetic(59);

    private Teacher getTeacher() {
        Teacher teacher = new Teacher();

        teacher.setName(textUnder60Characters);
        teacher.setSurname(textUnder60Characters);
        teacher.setUsername(textUnder60Characters);
        teacher.setPassword(textUnder60Characters);

        return teacher;
    }

    @Test
    public void testTeacherNameTooLong() {
        ValidationException validationException = Assertions.assertThrows(ValidationException.class, () -> {
            Teacher teacher = getTeacher();

            teacher.setName(textOver64Characters);

            teacherValidator.validate(teacher);
        });

        Assertions.assertEquals("422 UNPROCESSABLE_ENTITY \"Teacher name cannot exceed 64 characters\"", validationException.getMessage());
    }

    @Test
    public void testTeacherNameBlank() {
        ValidationException validationException = Assertions.assertThrows(ValidationException.class, () -> {
            Teacher teacher = getTeacher();

            teacher.setName(textBlank);

            teacherValidator.validate(teacher);
        });

        Assertions.assertEquals("422 UNPROCESSABLE_ENTITY \"Teacher name cannot be blank\"", validationException.getMessage());
    }

    @Test
    public void testTeacherSurnameTooLong() {
        ValidationException validationException = Assertions.assertThrows(ValidationException.class, () -> {
            Teacher teacher = getTeacher();

            teacher.setSurname(textOver64Characters);

            teacherValidator.validate(teacher);
        });

        Assertions.assertEquals("422 UNPROCESSABLE_ENTITY \"Teacher surname cannot exceed 64 characters\"", validationException.getMessage());
    }

    @Test
    public void testTeacherSurnameBlank() {
        ValidationException validationException = Assertions.assertThrows(ValidationException.class, () -> {
            Teacher teacher = getTeacher();

            teacher.setSurname(textBlank);

            teacherValidator.validate(teacher);
        });

        Assertions.assertEquals("422 UNPROCESSABLE_ENTITY \"Teacher surname cannot be blank\"", validationException.getMessage());
    }

    @Test
    public void testTeacherUsernameTooLong() {
        ValidationException validationException = Assertions.assertThrows(ValidationException.class, () -> {
            Teacher teacher = getTeacher();

            teacher.setUsername(textOver64Characters);

            teacherValidator.validate(teacher);
        });

        Assertions.assertEquals("422 UNPROCESSABLE_ENTITY \"Teacher username cannot exceed 64 characters\"", validationException.getMessage());
    }

    @Test
    public void testTeacherUsernameBlank() {
        ValidationException validationException = Assertions.assertThrows(ValidationException.class, () -> {
            Teacher teacher = getTeacher();

            teacher.setUsername(textBlank);

            teacherValidator.validate(teacher);
        });

        Assertions.assertEquals("422 UNPROCESSABLE_ENTITY \"Teacher username cannot be blank\"", validationException.getMessage());
    }

    @Test
    public void testTeacherPasswordTooLong() {
        ValidationException validationException = Assertions.assertThrows(ValidationException.class, () -> {
            Teacher teacher = getTeacher();

            teacher.setPassword(textOver64Characters);

            teacherValidator.validate(teacher);
        });

        Assertions.assertEquals("422 UNPROCESSABLE_ENTITY \"Teacher password cannot exceed 60 characters\"", validationException.getMessage());
    }

    @Test
    public void testTeacherPasswordBlank() {
        ValidationException validationException = Assertions.assertThrows(ValidationException.class, () -> {
            Teacher teacher = getTeacher();

            teacher.setPassword(textBlank);

            teacherValidator.validate(teacher);
        });

        Assertions.assertEquals("422 UNPROCESSABLE_ENTITY \"Teacher password cannot be blank\"", validationException.getMessage());
    }

    @Test
    public void testTeacherInfoAllCorrect() {
        Assertions.assertDoesNotThrow(() -> {
            Teacher teacher = getTeacher();

            teacherValidator.validate(teacher);
        });
    }
}