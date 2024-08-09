package com.sparta.msa_exam.product.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // 400
    INVALID_PARAMETER("p40000", HttpStatus.BAD_REQUEST, "유효하지 않는 파라미터입니다."),
    MISSING_REQUEST_PARAMETER("p40001", HttpStatus.BAD_REQUEST, "필수 파라미터가 누락되었습니다."),
    MISSING_REQUEST_BODY("p40002", HttpStatus.BAD_REQUEST, "요청 바디가 누락되었습니다."),

    // 401


    // 403

    // 404
    NOT_FOUND_END_POINT("p40400", HttpStatus.NOT_FOUND, "존재하지 않는 엔드포인트입니다."),
    PRODUCT_NOT_FOUND("p40401", HttpStatus.NOT_FOUND, "상품을 찾을 수 없습니다."),

    // 500
    SERVER_ERROR("p50000", HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다.");

    private final String code;
    private final HttpStatus httpStatus;
    private final String message;
}
