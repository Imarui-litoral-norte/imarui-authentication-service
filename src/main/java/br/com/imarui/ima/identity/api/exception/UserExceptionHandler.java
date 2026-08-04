package br.com.imarui.ima.identity.api.exception;

import br.com.imarui.ima.authentication.core.application.exceptions.login.InvalidCredentialsException;
import br.com.imarui.ima.identity.core.application.exception.identity.RegistrationConflictException;
import br.com.imarui.ima.identity.core.application.exception.identity.UserCpfInvalidException;
import br.com.imarui.ima.identity.core.application.exception.identity.UserDisabledException;
import br.com.imarui.ima.identity.core.application.exception.identity.UserIdNotFoundException;
import br.com.imarui.ima.identity.core.domain.exception.identity.IdentityAlreadyDisabledException;
import br.com.imarui.ima.identity.core.domain.exception.identity.IdentityIdRequiredException;
import br.com.imarui.ima.identity.core.domain.exception.identity.IdentityNotDisabledException;
import br.com.imarui.ima.identity.core.domain.exception.identity.IdentityNowInstantRequiredException;
import br.com.imarui.ima.platform.web.exception.ApiErrorResponse;
import br.com.imarui.ima.platform.web.exception.ApiExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "br.com.imarui.authentication")
public class UserExceptionHandler extends ApiExceptionHandler {

    @ExceptionHandler(IdentityIdRequiredException.class)
    public ResponseEntity<ApiErrorResponse> handleUserIdRequired(IdentityIdRequiredException exception) {
        return buildErrorResponse(
                "USER_ID_REQUIRED",
                exception.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(IdentityNowInstantRequiredException.class)
    public ResponseEntity<ApiErrorResponse> handleUserNowInstantRequired(IdentityNowInstantRequiredException exception) {
        return buildErrorResponse(
                "USER_NOW_INSTANT_REQUIRED",
                exception.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UserCpfInvalidException.class)
    public ResponseEntity<ApiErrorResponse> handleUserCpfInvalid(UserCpfInvalidException exception) {
        return buildErrorResponse(
                "USER_CPF_INVALID",
                exception.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UserDisabledException.class)
    public ResponseEntity<ApiErrorResponse> handleUserDisabled(UserDisabledException exception) {
        return buildErrorResponse(
                "USER_DISABLED",
                exception.getMessage(),
                HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler(UserIdNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleUserIdNotFound(UserIdNotFoundException exception) {
        return buildErrorResponse(
                "USER_ID_NOT_FOUND",
                exception.getMessage(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(IdentityAlreadyDisabledException.class)
    public ResponseEntity<ApiErrorResponse> handleUserAlreadyDisabled(
            IdentityAlreadyDisabledException exception
    ) {
        return buildErrorResponse(
                "USER_ALREADY_DISABLED",
                exception.getMessage(),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(IdentityNotDisabledException.class)
    public ResponseEntity<ApiErrorResponse> handleUserNotDisabled(
            IdentityNotDisabledException exception
    ) {
        return buildErrorResponse(
                "USER_NOT_DISABLED",
                exception.getMessage(),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidCredentials(
            InvalidCredentialsException exception
    ) {
        return buildErrorResponse(
                "INVALID_CREDENTIALS",
                exception.getMessage(),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(RegistrationConflictException.class)
    public ResponseEntity<ApiErrorResponse> handleRegistrationConflict(
            RegistrationConflictException exception
    ) {
        return buildErrorResponse(
                "REGISTRATION_CONFLICT",
                exception.getMessage(),
                HttpStatus.CONFLICT
        );
    }
}
