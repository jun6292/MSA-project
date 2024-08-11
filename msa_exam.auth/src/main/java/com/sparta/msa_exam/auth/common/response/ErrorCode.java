package com.sparta.msa_exam.auth.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // 400
    INVALID_PARAMETER("a40000", HttpStatus.BAD_REQUEST, "유효하지 않는 파라미터입니다."),
    MISSING_REQUEST_PARAMETER("a40001", HttpStatus.BAD_REQUEST, "필수 파라미터가 누락되었습니다."),
    MISSING_REQUEST_BODY("a40002", HttpStatus.BAD_REQUEST, "요청 바디가 누락되었습니다."),
    DUPLICATED_USER("a40003", HttpStatus.BAD_REQUEST, "이미 존재하는 유저입니다."),

    // 401
    UNAUTHORIZED("a40100", HttpStatus.UNAUTHORIZED, "Invalid user ID or password"),

    // 403

    // 404
    NOT_FOUND_END_POINT("a40400", HttpStatus.NOT_FOUND, "존재하지 않는 엔드포인트입니다."),
    NOT_FOUND_USER("a40401", HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다."),

    // 500
    SERVER_ERROR("a50000", HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다.");

    private final String code;
    private final HttpStatus httpStatus;
    private final String message;
}
