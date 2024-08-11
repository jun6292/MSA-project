package com.sparta.msa_exam.auth.common.exception;

import com.sparta.msa_exam.auth.common.response.ErrorCode;

public class CommonException extends RuntimeException {
    private final ErrorCode errorCode;

    public CommonException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return errorCode.getMessage();
    }
}
