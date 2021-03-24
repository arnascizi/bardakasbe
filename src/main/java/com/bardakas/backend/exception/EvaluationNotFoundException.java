package com.bardakas.backend.exception;

public class EvaluationNotFoundException extends RuntimeException {
    public EvaluationNotFoundException(long id) {
        super("Evaluation with the id of " + id + " does not exist!");
    }
}
