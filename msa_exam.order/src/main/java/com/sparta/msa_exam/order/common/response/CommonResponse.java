package com.sparta.msa_exam.order.common.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.msa_exam.order.common.exception.CommonException;
import com.sparta.msa_exam.order.common.exception.RestApiException;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

public record CommonResponse<T>(
        @JsonIgnore HttpStatus httpStatus,
        @NotNull Boolean success,
        @Nullable T data,
        @Nullable RestApiException error
) {
    // success
    public static <T> CommonResponse<T> ok(@Nullable T data) {
        return new CommonResponse<T>(HttpStatus.OK, true, data, null);
    }

    public static <T> CommonResponse<T> created(@Nullable final T data) {
        return new CommonResponse<>(HttpStatus.CREATED, true, data, null);
    }

    // fail
    public static CommonResponse<Object> fail(final CommonException e) {
        return new CommonResponse<>(e.getErrorCode().getHttpStatus(), false, null, new RestApiException(e.getErrorCode()));
    }

    public static CommonResponse<Object> fail(final MethodArgumentTypeMismatchException e) {
        return new CommonResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, false, null, new RestApiException(ErrorCode.INVALID_PARAMETER));
    }

    public static CommonResponse<Object> fail(final MissingServletRequestParameterException e) {
        return new CommonResponse<>(HttpStatus.BAD_REQUEST, false, null, new RestApiException(ErrorCode.MISSING_REQUEST_PARAMETER));
    }

    public static CommonResponse<Object> fail(final HttpMessageNotReadableException e) {
        return new CommonResponse<>(HttpStatus.BAD_REQUEST, false, null, new RestApiException(ErrorCode.MISSING_REQUEST_BODY));
    }
}
