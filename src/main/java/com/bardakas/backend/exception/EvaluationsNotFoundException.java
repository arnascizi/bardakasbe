package com.bardakas.backend.exception;

public class EvaluationsNotFoundException extends RuntimeException {
    public EvaluationsNotFoundException() {
        super("Evaluations not exist!");
    }
}
