package com.sparta.msa_exam.order.common.exception;

import com.sparta.msa_exam.order.common.response.CommonResponse;
import com.sparta.msa_exam.order.common.response.ErrorCode;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    // HTTP method 가 잘못된 경우, 존재하지 않는 엔드포인트를 호출한 경우
    @ExceptionHandler(value = {NoHandlerFoundException.class, HttpRequestMethodNotSupportedException.class})
    public CommonResponse<?> handleNoPageFoundException(Exception e) {
        log.error("handleNoPageFoundException() in GlobalExceptionHandler throw NoHandlerFoundException : {}", e.getMessage());
        return CommonResponse.fail(new CommonException(ErrorCode.NOT_FOUND_END_POINT));
    }

    // 메소드의 인자 타입이 일치하지 않을 때 발생하는 예외
    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public CommonResponse<?> handleArgumentNotValidException(MethodArgumentTypeMismatchException e) {
        log.error("handleArgumentNotValidException() in GlobalExceptionHandler throw MethodArgumentTypeMismatchException : {}", e.getMessage());
        return CommonResponse.fail(e);
    }

    // Request Param 누락 예외
    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public CommonResponse<?> handleServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("handleArgumentNotValidException() in GlobalExceptionHandler throw MissingServletRequestParameterException : {}", e.getMessage());
        return CommonResponse.fail(e);
    }

    // Request Body 누락 예외
    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public CommonResponse<?> handleMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("handleArgumentNotValidException() in GlobalExceptionHandler throw HttpMessageNotReadableException : {}", e.getMessage());
        return CommonResponse.fail(e);
    }

    // @Valid 어노테이션을 사용하여 검증을 수행할 때 발생하는 예외
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public CommonResponse<?> handleArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("handleArgumentNotValidException() in GlobalExceptionHandler throw MethodArgumentNotValidException : {}", e.getMessage());
        return CommonResponse.fail(e);
    }

    // @Validated 어노테이션을 사용하여 검증을 수행할 때 발생하는 예외
    @ExceptionHandler(value = {ConstraintViolationException.class})
    public CommonResponse<?> handleArgumentNotValidException(ConstraintViolationException e) {
        log.error("handleArgumentNotValidException() in GlobalExceptionHandler throw ConstraintViolationException : {}", e.getMessage());
        return CommonResponse.fail(e);
    }

    // 직접 정의한 예외
    @ExceptionHandler(value = {CommonException.class})
    public CommonResponse<?> handleApiException(CommonException e) {
        log.error("handleApiException() in GlobalExceptionHandler throw CommonException : {}", e.getMessage());
        return CommonResponse.fail(e);
    }

    // 서버 내부 오류
    @ExceptionHandler(value = {Exception.class})
    public CommonResponse<?> handleException(Exception e) {
        log.error("handleException() in GlobalExceptionHandle throw Exception: {}", e.getMessage(), e);
        return CommonResponse.fail(new CommonException(ErrorCode.SERVER_ERROR));
    }
}
