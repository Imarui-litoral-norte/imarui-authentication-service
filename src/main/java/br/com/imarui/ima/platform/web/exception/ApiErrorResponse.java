package br.com.imarui.ima.platform.web.exception;

import java.time.Instant;

public record ApiErrorResponse(
        String code,
        String message,
        int status,
        Instant timestamp
) {
}
