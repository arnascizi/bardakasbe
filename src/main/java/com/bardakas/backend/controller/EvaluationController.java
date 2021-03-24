package com.bardakas.backend.controller;

import com.bardakas.backend.entity.dto.EvaluationDTO;
import com.bardakas.backend.exception.EvaluationNotFoundException;
import com.bardakas.backend.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/evaluations")
public class EvaluationController {

    private final EvaluationService evaluationService;

    @Autowired
    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @GetMapping
    public ResponseEntity<List<EvaluationDTO>> getEvaluations() {
        List<EvaluationDTO> list = evaluationService.getAllEvaluations();
        return new ResponseEntity<List<EvaluationDTO>>(list, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<EvaluationDTO> getEvaluationById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<EvaluationDTO>(evaluationService.getEvaluationsById(id), HttpStatus.OK);
        } catch (EvaluationNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<List<EvaluationDTO>> getEvaluationsByStudentId(@PathVariable("id") Long studentId) {
        try {
        List<EvaluationDTO> list = evaluationService.getAllEvaluationsByStudentId(studentId);
        return new ResponseEntity<List<EvaluationDTO>>(list, HttpStatus.OK);
        } catch (EvaluationNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<List<Evaluation>> getEvaluationsByStudentId(@PathVariable("id") Long studentId) {
        List<Evaluation> list = evaluationService.getAllEvaluationsByStudentId(studentId);
        return new ResponseEntity<List<Evaluation>>(list, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEvaluationById(@PathVariable("id") Long id) {
        try {
            evaluationService.deleteEvaluationById(id);
            return ResponseEntity.ok().build();
        } catch (EvaluationNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> addEvaluation(@RequestBody EvaluationDTO evaluationDTO) {
        evaluationService.createEvaluation(evaluationDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateEvaluation(@RequestBody EvaluationDTO evaluationDTO) {
        try {
            evaluationService.updateEvaluation(evaluationDTO);
            return ResponseEntity.ok().build();
        } catch (EvaluationNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
