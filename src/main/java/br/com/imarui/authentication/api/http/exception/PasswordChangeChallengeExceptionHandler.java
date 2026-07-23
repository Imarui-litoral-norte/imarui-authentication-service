package br.com.imarui.authentication.api.http.exception;

import br.com.imarui.authentication.core.application.exceptions.login.PasswordChangeChallengeInvalidException;
import br.com.imarui.authentication.core.domain.exception.passwordchangechallenge.PasswordChangeChallengeStateException;
import br.com.imarui.authentication.core.domain.exception.passwordchangechallenge.PasswordChangeChallengeValidationException;
import br.com.imarui.platform.web.exception.ApiErrorResponse;
import br.com.imarui.platform.web.exception.ApiExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(
        basePackages = "br.com.imarui.authentication"
)
public class PasswordChangeChallengeExceptionHandler
        extends ApiExceptionHandler {

    @ExceptionHandler(
            PasswordChangeChallengeValidationException.class
    )
    public ResponseEntity<ApiErrorResponse>
    handlePasswordChangeChallengeValidation(
            PasswordChangeChallengeValidationException exception
    ) {
        return buildErrorResponse(
                "PASSWORD_CHANGE_CHALLENGE_INVALID_DATA",
                exception.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(
            PasswordChangeChallengeStateException.class
    )
    public ResponseEntity<ApiErrorResponse>
    handlePasswordChangeChallengeState(
            PasswordChangeChallengeStateException exception
    ) {
        return buildErrorResponse(
                "PASSWORD_CHANGE_CHALLENGE_STATE_CONFLICT",
                exception.getMessage(),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(
            PasswordChangeChallengeInvalidException.class
    )
    public ResponseEntity<ApiErrorResponse>
    handlePasswordChangeChallengeInvalid(
            PasswordChangeChallengeInvalidException exception
    ) {
        return buildErrorResponse(
                "PASSWORD_CHANGE_CHALLENGE_INVALID",
                exception.getMessage(),
                HttpStatus.UNAUTHORIZED
        );
    }
}