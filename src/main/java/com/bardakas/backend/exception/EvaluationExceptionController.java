package com.bardakas.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EvaluationExceptionController {

    @ExceptionHandler(value = EvaluationNotFoundException.class)
    public ResponseEntity<Object> exception(EvaluationNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = StudentEvaluationsNotFoundException.class)
    public ResponseEntity<Object> exception(StudentEvaluationsNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
