package com.example.websimple.exception;

import com.example.websimple.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<ErrorResponse> handleIllegalAccessException(
            IllegalAccessException e) {
        log.error("IllegalAccessException is occurred.", e);

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
//                .header("newHeader", "Some Value")
                .body(new ErrorResponse(ErrorCode.TOO_BIG_ID_ERROR,
                        "IllegalAccessException is occurred."));
    }

    // 특정 인셉션들은 특정 클래스를 지정해주고, 여러개는 list로 넣을 수 있음 or 맨 마지막에 그냥 Exception e로 만 취급하기
    @ExceptionHandler(WebSampleException.class)
    public ResponseEntity<ErrorResponse> handleIllegalAccessException(
            WebSampleException e) {
        log.error("WebSampleException is occurred.", e);

        return ResponseEntity.status(HttpStatus.INSUFFICIENT_STORAGE)
//                .header("newHeader", "Some Value")
                .body(new ErrorResponse(e.getErrorCode(),
                        "WebSampleException is occurred."));
    }

    // 맨 마지막까지 타고 내려와서 걸릴 인셉션 -> 최후의 방어선
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleIllegalAccessException(
            Exception e) {
        log.error("Exception is occurred.", e);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR,
                        "Exception is occurred."));
    }
}
