package com.bardakas.backend.service;

import com.bardakas.backend.entity.db.Student;
import com.bardakas.backend.entity.dto.StudentDTO;
import com.bardakas.backend.exception.StudentNotFoundException;
import com.bardakas.backend.repository.StudentRepository;
import com.bardakas.backend.validator.StudentDTOValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private StudentRepository studentRepository;
    private StudentDTOValidator studentDTOValidator;
    private ModelMapper modelMapper;

    @Autowired
    public StudentService(StudentRepository studentRepository, StudentDTOValidator studentDTOValidator, ModelMapper modelMapper) {
        this.studentDTOValidator = studentDTOValidator;
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    public List<StudentDTO> getAll() {
        return studentRepository.findAll()
                .stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());
    }

    public StudentDTO getById(long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        return modelMapper.map(student, StudentDTO.class);
    }

    public void delete(long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        studentRepository.deleteById(id);
    }

    public void add(StudentDTO studentDTO) {
        studentDTOValidator.validate(studentDTO);
        Student student = modelMapper.map(studentDTO, Student.class);
        Optional<Student> studentOptional = studentRepository.findById(student.getId());
        if (studentOptional.isPresent()) {
            throw new RuntimeException();
        }
        studentRepository.save(student);

    }

    public void update(StudentDTO studentDTO) {
        studentDTOValidator.validate(studentDTO);
        Student student = modelMapper.map(studentDTO, Student.class);
        long id = student.getId();
        Student optionalStudent = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        studentRepository.save(student);
    }
}