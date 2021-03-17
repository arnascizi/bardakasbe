package com.bardakas.backend.controller;

import com.bardakas.backend.entity.dto.StudentDTO;
import com.bardakas.backend.exception.StudentNotFoundException;
import com.bardakas.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping
    public ResponseEntity<List<StudentDTO>> getStudents() {
        List<StudentDTO> list = studentService.getAll();
        return new ResponseEntity<List<StudentDTO>>(list, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable("id") Long id) {
        return new ResponseEntity<StudentDTO>(studentService.getById(id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") Long id) {
        try {
            studentService.delete(id);
            return ResponseEntity.ok().build();
        } catch (StudentNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> addStudent(@RequestBody StudentDTO studentDTO, UriComponentsBuilder builder) {
        studentService.add(studentDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/{id}").buildAndExpand(studentDTO.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDTO) {
        studentService.update(studentDTO);
        return new ResponseEntity<StudentDTO>(studentDTO, HttpStatus.OK);
    }

}
