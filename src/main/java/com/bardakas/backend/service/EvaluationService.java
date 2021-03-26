package com.bardakas.backend.service;

import com.bardakas.backend.entity.db.Evaluation;
import com.bardakas.backend.entity.dto.EvaluationDTO;
import com.bardakas.backend.exception.EvaluationExistException;
import com.bardakas.backend.exception.EvaluationNotFoundException;
import com.bardakas.backend.exception.StudentEvaluationsNotFoundException;
import com.bardakas.backend.repository.EvaluationRepository;
import com.bardakas.backend.validator.EvaluationDTOValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;
    private final ModelMapper modelMapper;
    private final EvaluationDTOValidator evaluationDTOValidator;

    @Autowired
    public EvaluationService(EvaluationRepository evaluationRepository, ModelMapper modelMapper, EvaluationDTOValidator evaluationDTOValidator) {
        this.evaluationRepository = evaluationRepository;
        this.modelMapper = modelMapper;
        this.evaluationDTOValidator = evaluationDTOValidator;
    }

    public Evaluation findEvaluationById(long id) {
        return evaluationRepository.findById(id).orElseThrow(() -> new EvaluationNotFoundException(id));

    }

    public List<EvaluationDTO> getAllEvaluations() {
        List<Evaluation> evaluations = evaluationRepository.findAll();
        return evaluations
                .stream()
                .map(evaluation -> modelMapper.map(evaluation, EvaluationDTO.class))
                .collect(Collectors.toList());
    }

    public EvaluationDTO getEvaluationsById(long id) throws EvaluationNotFoundException {
        Evaluation evaluation = findEvaluationById(id);
        return modelMapper.map(evaluation, EvaluationDTO.class);
    }

    public List<EvaluationDTO> getAllEvaluationsByStudentId(long studentId) throws StudentEvaluationsNotFoundException {
        List<EvaluationDTO> evaluationsByStudentId = evaluationRepository.findAllByStudentId(studentId)
                .stream()
                .map(evaluation -> modelMapper.map(evaluation, EvaluationDTO.class))
                .collect(Collectors.toList());
        if (evaluationsByStudentId.isEmpty()) {
            throw new StudentEvaluationsNotFoundException(studentId);
        }
        return evaluationsByStudentId;
    }

    public void deleteEvaluationById(long id) throws EvaluationNotFoundException {
        Evaluation evaluation = findEvaluationById(id);
        evaluationRepository.delete(evaluation);
    }

    public void updateEvaluation(EvaluationDTO evaluationDTO) throws EvaluationNotFoundException {
        evaluationDTOValidator.validate(evaluationDTO);
        if (containsSameStreamStudentTeacher(getAllEvaluations(), evaluationDTO)) {
            throw new EvaluationExistException();
        } else {
            long id = evaluationDTO.getId();
            Evaluation evaluation = findEvaluationById(id);
            evaluation = modelMapper.map(evaluationDTO, Evaluation.class);
            evaluation.setId(id);
            evaluationRepository.save(evaluation);
        }
    }

    public void createEvaluation(EvaluationDTO evaluationDTO) throws EvaluationExistException {
        evaluationDTOValidator.validate(evaluationDTO);
        if (containsSameStreamStudentTeacher(getAllEvaluations(), evaluationDTO)) {
            throw new EvaluationExistException();
        } else {
            Evaluation evaluation = modelMapper.map(evaluationDTO, Evaluation.class);
            evaluationRepository.save(evaluation);
        }
    }

    public boolean containsSameStreamStudentTeacher(List<EvaluationDTO> list, EvaluationDTO evaluation) {
        return list.stream().anyMatch(eval -> (eval.getStream().equals(evaluation.getStream()) &&
                eval.getStudentId().equals(evaluation.getStudentId()) && eval.getTeacherId().equals(evaluation.getTeacherId()))
        );
    }
}

