package com.umc.bios.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 공통 에러
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // Member
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "ME4001", "사용자가 존재하지 않습니다."),
    NICKNAME_NOT_FOUND(HttpStatus.BAD_REQUEST, "ME4002", "닉네임은 필수입니다."),

    // webhook
    FAILED_SEND_WEBHOOK(HttpStatus.BAD_REQUEST, "WH4001", "웹훅 전송에 실패하였습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReason getReason() {
        return ErrorReason.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReason getReasonHttpStatus() {
        return ErrorReason.builder()
                .message(message)
                .code(code)
                .status(httpStatus)
                .isSuccess(false)
                .build();
    }
}
