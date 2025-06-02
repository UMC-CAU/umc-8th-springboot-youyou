package com.umc.bios.global.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ErrorReason {

    private HttpStatus status;

    private final boolean isSuccess;
    private final String code;
    private final String message;

    public boolean getIsSuccess() { return isSuccess; }
}
