package br.com.imarui.authentication.api.http.exception;

import br.com.imarui.authentication.core.application.exceptions.tokenhash.TokenInvalidException;
import br.com.imarui.authentication.core.application.exceptions.tokenhash.TokenNotFoundException;
import br.com.imarui.platform.web.exception.ApiErrorResponse;
import br.com.imarui.platform.web.exception.ApiExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "br.com.imarui.authentication")
public class TokenHashExceptionHandler extends ApiExceptionHandler {

    @ExceptionHandler(TokenInvalidException.class)
    public ResponseEntity<ApiErrorResponse> handleTokenInvalid(TokenInvalidException exception) {
        return buildErrorResponse(
                "TOKEN_INVALID",
                exception.getMessage(),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleTokenNotFound(TokenNotFoundException exception) {
        return buildErrorResponse(
                "TOKEN_NOT_FOUND",
                exception.getMessage(),
                HttpStatus.NOT_FOUND
        );
    }
}