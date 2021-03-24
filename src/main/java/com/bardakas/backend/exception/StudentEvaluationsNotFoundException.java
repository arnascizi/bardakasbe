package com.bardakas.backend.exception;

public class StudentEvaluationsNotFoundException extends RuntimeException {
    public StudentEvaluationsNotFoundException(long id) {
        super("Evaluation with studentId of " + id + " does not exist!");
    }
}
