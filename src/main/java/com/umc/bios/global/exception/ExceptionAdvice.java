package com.umc.bios.global.exception;

import com.umc.bios.global.response.CommonResponse;
import com.umc.bios.global.response.ErrorStatus;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestControllerAdvice
@Hidden
@RequiredArgsConstructor
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> validation(ConstraintViolationException e, WebRequest request) {
        String errorMessage = e.getConstraintViolations().stream()
                .map(constraintViolation -> constraintViolation.getMessage())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("ConstraintViolationException 추출 도중 에러 발생"));

        ErrorStatus errorStatus = ErrorStatus.valueOf(errorMessage);
        return (ResponseEntity) CommonResponse.onFailure(errorStatus, null);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                               HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, String> errors = new LinkedHashMap<>();

        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
            String fieldName = fieldError.getField();
            String errorMessage = Optional.ofNullable(fieldError.getDefaultMessage()).orElse("");
            errors.merge(fieldName, errorMessage, (existingErrorMessage, newErrorMessage) ->
                    existingErrorMessage + ", " + newErrorMessage);
        });

        return (ResponseEntity) CommonResponse.onFailure(ErrorStatus._BAD_REQUEST, errors);
    }

    @ExceptionHandler
    public ResponseEntity<?> exception(Exception e, HttpServletRequest request) {

        log.error("500 서버 오류", e);

        return CommonResponse.onFailure(ErrorStatus._INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<Object> onThrowException(CustomException e, HttpServletRequest request) {
        logError(e, request);
        return (ResponseEntity) CommonResponse.onFailure(e.getErrorCode(), null);
    }

    // console log 출력
    private void logError(Exception e, HttpServletRequest request) {
        log.error("Request URI : [{}] {}", request.getMethod(), request.getRequestURI());
        log.error("Exception : ", e);
    }
}