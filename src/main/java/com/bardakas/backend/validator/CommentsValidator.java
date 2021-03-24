package com.bardakas.backend.validator;

import com.bardakas.backend.exception.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class CommentsValidator extends Validator<String> {
    @Override
    public void validate(String attribute, String message) {
        if(attribute.length()>255) {
            throw new ValidationException(message);
        }

    }
}
