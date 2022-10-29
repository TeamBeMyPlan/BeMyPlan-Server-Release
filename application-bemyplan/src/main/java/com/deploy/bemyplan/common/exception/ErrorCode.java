package com.deploy.bemyplan.common.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.deploy.bemyplan.common.exception.ErrorStatusCode.BAD_GATEWAY;
import static com.deploy.bemyplan.common.exception.ErrorStatusCode.BAD_REQUEST;
import static com.deploy.bemyplan.common.exception.ErrorStatusCode.CONFLICT;
import static com.deploy.bemyplan.common.exception.ErrorStatusCode.FORBIDDEN;
import static com.deploy.bemyplan.common.exception.ErrorStatusCode.INTERNAL_SERVER;
import static com.deploy.bemyplan.common.exception.ErrorStatusCode.METHOD_NOT_ALLOWED;
import static com.deploy.bemyplan.common.exception.ErrorStatusCode.NOT_ACCEPTABLE;
import static com.deploy.bemyplan.common.exception.ErrorStatusCode.NOT_FOUND;
import static com.deploy.bemyplan.common.exception.ErrorStatusCode.SERVICE_UNAVAILABLE;
import static com.deploy.bemyplan.common.exception.ErrorStatusCode.UNAUTHORIZED;
import static com.deploy.bemyplan.common.exception.ErrorStatusCode.UNSUPPORTED_MEDIA_TYPE;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    // 400 Bad Request
    VALIDATION_AUTH_TOKEN_EXCEPTION(BAD_REQUEST, "만료되거나 유효하지 않은 인증 토큰입니다"),
    VALIDATION_SESSION_EXCEPTION(BAD_REQUEST, "잘못된 세션입니다."),
    VALIDATION_EXCEPTION(BAD_REQUEST, "잘못된 요청입니다"),
    VALIDATION_ENUM_VALUE_EXCEPTION(BAD_REQUEST, "잘못된 Enum 값 입니다"),
    VALIDATION_REQUEST_MISSING_EXCEPTION(BAD_REQUEST, "필수적인 요청 값이 입력되지 않았습니다"),
    VALIDATION_WRONG_TYPE_EXCEPTION(BAD_REQUEST, "잘못된 타입이 입력되었습니다."),
    VALIDATION_SOCIAL_TYPE_EXCEPTION(BAD_REQUEST, "잘못된 소셜 프로바이더 입니다."),

    // 401 UnAuthorized
    UNAUTHORIZED_EXCEPTION(UNAUTHORIZED, "세션이 만료되었습니다. 다시 로그인 해주세요"),

    // 403 Forbidden
    FORBIDDEN_EXCEPTION(FORBIDDEN, "허용하지 않는 요청입니다."),

    // 404 Not Found
    NOT_FOUND_EXCEPTION(NOT_FOUND, "존재하지 않습니다"),
    NOT_FOUND_USER_EXCEPTION(NOT_FOUND, "탈퇴하거나 존재하지 않는 유저입니다"),
    NOT_FOUND_PLAN_EXCEPTION(NOT_FOUND, "존재하지 않는 여행일정입니다"),
    NOT_FOUND_SCRAP_EXCEPTION(NOT_FOUND, "스크랩 정보가 존재하지 않습니다."),

    // 405 Method Not Allowed
    METHOD_NOT_ALLOWED_EXCEPTION(METHOD_NOT_ALLOWED, "지원하지 않는 메소드 입니다"),

    // 406 Not Acceptable
    NOT_ACCEPTABLE_EXCEPTION(NOT_ACCEPTABLE, "Not Acceptable"),

    // 409 Conflict
    CONFLICT_EXCEPTION(CONFLICT, "이미 존재합니다"),
    CONFLICT_ORDER_PLAN(CONFLICT, "이미 구매한 일정입니다"),
    CONFLICT_NICKNAME_EXCEPTION(CONFLICT, "이미 사용중인 닉네임입니다.\n다른 닉네임을 이용해주세요"),
    CONFLICT_USER_EXCEPTION(CONFLICT, "이미 해당 계정으로 회원가입하셨습니다.\n로그인 해주세요"),

    // 415 Unsupported Media Type
    UNSUPPORTED_MEDIA_TYPE_EXCEPTION(UNSUPPORTED_MEDIA_TYPE, "해당하는 미디어 타입을 지원하지 않습니다."),

    // 500 Internal Server Exception
    INTERNAL_SERVER_EXCEPTION(INTERNAL_SERVER, "예상치 못한 서버 에러가 발생하였습니다."),

    // 502 Bad Gateway
    BAD_GATEWAY_EXCEPTION(BAD_GATEWAY, "일시적인 에러가 발생하였습니다.\n잠시 후 다시 시도해주세요!"),

    // 503 Service UnAvailable
    SERVICE_UNAVAILABLE_EXCEPTION(SERVICE_UNAVAILABLE, "현재 점검 중입니다.\n잠시 후 다시 시도해주세요!"),
    ;

    private final ErrorStatusCode statusCode;
    private final String message;

    public int getStatus() {
        return statusCode.getStatus();
    }
}