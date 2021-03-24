package com.bardakas.backend.exception;

public class EvaluationExistException extends RuntimeException {
    public EvaluationExistException() {
        super("Evaluation with this stream, teacherId and studentId exist");
    }
}
