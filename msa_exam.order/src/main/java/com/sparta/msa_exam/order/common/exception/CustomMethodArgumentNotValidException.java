package com.sparta.msa_exam.order.common.exception;

import com.sparta.msa_exam.order.common.response.ErrorCode;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.Getter;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

@Getter
public class CustomMethodArgumentNotValidException extends RestApiException {
    private final Map<String, String> errorFields;

    public CustomMethodArgumentNotValidException(final MethodArgumentNotValidException methodArgumentNotValidException) {
        super(ErrorCode.INVALID_PARAMETER);

        this.errorFields = new HashMap<>();
        methodArgumentNotValidException.getBindingResult()
                .getAllErrors().forEach(e -> this.errorFields.put(((FieldError) e).getField(), e.getDefaultMessage()));
    }

    public CustomMethodArgumentNotValidException(final ConstraintViolationException constraintViolationException) {
        super(ErrorCode.INVALID_PARAMETER);

        this.errorFields = new HashMap<>();

        for (ConstraintViolation<?> constraintViolation : constraintViolationException.getConstraintViolations()) {
            errorFields.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
        }
    }
}
