package com.umc.bios.global.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
public class CommonResponse<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("result")
    private T result;

    // Success - 200 OK
    public static <T> CommonResponse<T> onSuccess(T result) {
        return new CommonResponse<>(true, SuccessStatus._OK.getCode(), SuccessStatus._OK.getMessage(), result);
    }

    public static <T> CommonResponse<T> of(BaseCode code, T result) {
        return new CommonResponse<>(true, code.getReasonHttpStatus().getCode(), code.getReasonHttpStatus().getMessage(), result);
    }

    public static CommonResponse<Void> of(BaseCode code) {
        return new CommonResponse<>(true, code.getReasonHttpStatus().getCode(), code.getReasonHttpStatus().getMessage(), null);
    }

    // Failure with data
    public static <T> ResponseEntity<CommonResponse<T>> onFailure(BaseErrorCode errorCode, T data) {
        CommonResponse<T> body = new CommonResponse<>(false, errorCode.getReasonHttpStatus().getCode(), errorCode.getReasonHttpStatus().getMessage(), data);
        return ResponseEntity.status(errorCode.getReasonHttpStatus().getStatus()).body(body);
    }
}
