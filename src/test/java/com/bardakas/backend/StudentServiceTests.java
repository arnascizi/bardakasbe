package com.bardakas.backend;

import com.bardakas.backend.entity.db.Student;
import com.bardakas.backend.entity.dto.StudentDTO;
import com.bardakas.backend.exception.StudentNotFoundException;
import com.bardakas.backend.exception.ValidationException;
import com.bardakas.backend.repository.StudentRepository;
import com.bardakas.backend.service.StudentService;
import com.bardakas.backend.validator.StudentDTOValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@SpringBootTest
public class StudentServiceTests {

    @Mock
    private StudentDTOValidator studentDTOValidator;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private StudentService studentService = new StudentService(studentRepository, studentDTOValidator, modelMapper);


    @Test
    public void getById_StudentWithIdExists_ReturnsStudentDTO() {
        Student testStudent = new Student();
        testStudent.setId(1);
        testStudent.setName("TestName");
        testStudent.setSurname("TestSurname");
        StudentDTO testStudentDTO = new StudentDTO();
        testStudentDTO.setId(1);
        testStudentDTO.setName("TestName");
        testStudentDTO.setSurname("TestSurname");
        when(modelMapper.map(testStudent, StudentDTO.class)).thenReturn(testStudentDTO);
        when(studentRepository.findById(testStudent.getId())).thenReturn(Optional.of(testStudent));
        Assertions.assertEquals(testStudentDTO.getName(), studentService.getById(testStudentDTO.getId()).getName());
        Assertions.assertEquals(testStudentDTO.getSurname(), studentService.getById(testStudentDTO.getId()).getSurname());
    }

    @Test
    public void getById_StudentWithIdDoesNotExist_ExceptionThrown() {
        long id = anyLong();
        when(studentRepository.findById(id)).thenThrow(new StudentNotFoundException(id));
        Assertions.assertThrows(StudentNotFoundException.class, () -> {
            Assertions.assertEquals(id, studentService.getById(id).getId());
        });
    }

    @Test
    public void delete_StudentWithIdExists_ExceptionNotThrown() {
        Student testStudent = new Student();
        testStudent.setId(1);
        testStudent.setName("TestName");
        testStudent.setSurname("TestSurname");
        when(studentRepository.findById(testStudent.getId())).thenReturn(java.util.Optional.of(testStudent));
        studentService.delete(testStudent.getId());
        verify(studentRepository).deleteById(testStudent.getId());

    }

    @Test
    public void delete_StudentWithIdDoesNotExist_ExceptionThrown() {
        long id = anyLong();
        when(studentRepository.findById(id)).thenThrow(new StudentNotFoundException(id));
        Assertions.assertThrows(StudentNotFoundException.class, () -> {
            studentService.delete(id);
        });
    }

    @Test
    public void add_StudentPassesValidation_ExceptionNotThrown() {
        Student testStudent = new Student();
        testStudent.setId(1);
        testStudent.setName("TestName");
        testStudent.setSurname("TestSurname");
        StudentDTO testStudentDTO = new StudentDTO();
        testStudentDTO.setId(1);
        testStudentDTO.setName("TestName");
        testStudentDTO.setSurname("TestSurname");
        when(modelMapper.map(testStudentDTO, Student.class)).thenReturn(testStudent);
        studentService.add(testStudentDTO);
        verify(studentRepository).save(testStudent);
    }

    @Test
    public void add_StudentDoesNotPassValidation_ExceptionThrown() {
        Student testStudent = new Student();
        testStudent.setId(1);
        testStudent.setName("TestName");
        testStudent.setSurname("TestSurname");
        StudentDTO testStudentDTO = modelMapper.map(testStudent, StudentDTO.class);
        when(studentRepository.findById(testStudent.getId())).thenReturn(Optional.empty());
        doThrow(new ValidationException("")).when(studentDTOValidator).validate(testStudentDTO);
        Assertions.assertThrows(ValidationException.class, () -> {
            studentService.add(testStudentDTO);
        });
    }

    @Test
    public void update_StudentWithIdDoesNotExist_ExceptionThrown() {
        Student testStudent = new Student();
        testStudent.setId(1);
        testStudent.setName("TestName");
        testStudent.setSurname("TestSurname");
        StudentDTO testStudentDTO = new StudentDTO();
        testStudentDTO.setId(1);
        testStudentDTO.setName("TestName");
        testStudentDTO.setSurname("TestSurname");
        when(modelMapper.map(testStudentDTO, Student.class)).thenReturn(testStudent);
        when(studentRepository.findById(testStudent.getId())).thenThrow(new StudentNotFoundException(testStudent.getId()));
        Assertions.assertThrows(StudentNotFoundException.class, () -> {
           studentService.update(testStudentDTO);
        });
    }

    @Test
    public void update_StudentDoesNotPassValidation_ExceptionThrown() {
        Student testStudent = new Student();
        testStudent.setId(1);
        testStudent.setName("TestName");
        testStudent.setSurname("TestSurname");
        StudentDTO testStudentDTO = new StudentDTO();
        testStudentDTO.setId(1);
        testStudentDTO.setName("TestName");
        testStudentDTO.setSurname("TestSurname");
        when(modelMapper.map(testStudentDTO, Student.class)).thenReturn(testStudent);
        when(studentRepository.findById(testStudent.getId())).thenReturn(Optional.of(testStudent));
        doThrow(new ValidationException("")).when(studentDTOValidator).validate(testStudentDTO);
        Assertions.assertThrows(ValidationException.class, () -> {
           studentService.update(testStudentDTO);
        });
    }

    @Test
    public void update_StudentWithIdExistsAndPassesValidation_ExceptionNotThrown() {
        Student testStudent = new Student();
        testStudent.setId(1);
        testStudent.setName("TestName");
        testStudent.setSurname("TestSurname");
        StudentDTO testStudentDTO = new StudentDTO();
        testStudentDTO.setId(1);
        testStudentDTO.setName("TestName");
        testStudentDTO.setSurname("TestSurname");
        when(modelMapper.map(testStudentDTO, Student.class)).thenReturn(testStudent);
        when(studentRepository.findById(testStudent.getId())).thenReturn(Optional.of(testStudent));
        studentService.update(testStudentDTO);
        verify(studentRepository).save(testStudent);
    }
}
