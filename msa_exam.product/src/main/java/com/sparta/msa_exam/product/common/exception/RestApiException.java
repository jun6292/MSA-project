package com.sparta.msa_exam.product.common.exception;

import com.sparta.msa_exam.product.common.response.ErrorCode;
import lombok.Getter;

@Getter
public class RestApiException {
    private final String code;
    private final String message;

    public RestApiException(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }
}
