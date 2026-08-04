package br.com.imarui.ima.platform.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

public abstract class ApiExceptionHandler {

    protected ResponseEntity<ApiErrorResponse> buildErrorResponse(
            String code,
            String message,
            HttpStatus status
    ) {
        ApiErrorResponse response = new ApiErrorResponse(
                code,
                message,
                status.value(),
                Instant.now()
        );

        return ResponseEntity.status(status).body(response);
    }
}
