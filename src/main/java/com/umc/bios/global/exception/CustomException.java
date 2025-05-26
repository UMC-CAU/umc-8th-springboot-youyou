package com.umc.bios.global.exception;

import com.umc.bios.global.response.ErrorReason;
import com.umc.bios.global.response.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {

    private BaseErrorCode errorCode;

    public ErrorReason getErrorReason() {
        return this.errorCode.getReason();
    }

    public ErrorReason getErrorReasonHttpStatus() {
        return this.errorCode.getReasonHttpStatus();
    }

    public BaseErrorCode getErrorCode() {
        return this.errorCode;
    }

    public static CustomException of(BaseErrorCode errorCode) { return new CustomException(errorCode); }
}
