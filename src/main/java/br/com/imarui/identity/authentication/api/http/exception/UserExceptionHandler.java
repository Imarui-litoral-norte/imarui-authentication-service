package br.com.imarui.identity.authentication.api.http.exception;

import br.com.imarui.identity.authentication.core.application.exceptions.login.InvalidCredentialsException;
import br.com.imarui.identity.identity.core.application.exceptions.user.RegistrationConflictException;
import br.com.imarui.identity.identity.core.application.exceptions.user.UserCpfInvalidException;
import br.com.imarui.identity.identity.core.application.exceptions.user.UserDisabledException;
import br.com.imarui.identity.identity.core.application.exceptions.user.UserIdNotFoundException;
import br.com.imarui.identity.identity.core.domain.exceptions.user.UserAlreadyDisabledException;
import br.com.imarui.identity.identity.core.domain.exceptions.user.UserBlockedForLoginException;
import br.com.imarui.identity.identity.core.domain.exceptions.user.UserCannotChangePasswordException;
import br.com.imarui.identity.identity.core.domain.exceptions.user.UserIdRequiredException;
import br.com.imarui.identity.identity.core.domain.exceptions.user.UserLockoutException;
import br.com.imarui.identity.identity.core.domain.exceptions.user.UserNewHashRequiredException;
import br.com.imarui.identity.identity.core.domain.exceptions.user.UserNotDisabledException;
import br.com.imarui.identity.identity.core.domain.exceptions.user.UserNotLockoutException;
import br.com.imarui.identity.identity.core.domain.exceptions.user.UserNowInstantRequiredException;
import br.com.imarui.identity.identity.core.domain.exceptions.user.UserPasswordChangeRequiredException;
import br.com.imarui.identity.platform.web.exception.ApiErrorResponse;
import br.com.imarui.identity.platform.web.exception.ApiExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "br.com.imarui.authentication")
public class UserExceptionHandler extends ApiExceptionHandler {

    @ExceptionHandler(UserBlockedForLoginException.class)
    public ResponseEntity<ApiErrorResponse> handleUserBlockedForLogin(UserBlockedForLoginException exception) {
        return buildErrorResponse(
                "USER_BLOCKED_FOR_LOGIN",
                exception.getMessage(),
                HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler(UserCannotChangePasswordException.class)
    public ResponseEntity<ApiErrorResponse> handleUserCannotChangePassword(UserCannotChangePasswordException exception) {
        return buildErrorResponse(
                "USER_CANNOT_CHANGE_PASSWORD",
                exception.getMessage(),
                HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler(UserIdRequiredException.class)
    public ResponseEntity<ApiErrorResponse> handleUserIdRequired(UserIdRequiredException exception) {
        return buildErrorResponse(
                "USER_ID_REQUIRED",
                exception.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UserLockoutException.class)
    public ResponseEntity<ApiErrorResponse> handleUserLockout(UserLockoutException exception) {
        return buildErrorResponse(
                "USER_LOCKOUT",
                exception.getMessage(),
                HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler(UserNewHashRequiredException.class)
    public ResponseEntity<ApiErrorResponse> handleUserNewHashRequired(UserNewHashRequiredException exception) {
        return buildErrorResponse(
                "USER_NEW_HASH_REQUIRED",
                exception.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UserNotLockoutException.class)
    public ResponseEntity<ApiErrorResponse> handleUserNotLockout(UserNotLockoutException exception) {
        return buildErrorResponse(
                "USER_NOT_LOCKOUT",
                exception.getMessage(),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(UserNowInstantRequiredException.class)
    public ResponseEntity<ApiErrorResponse> handleUserNowInstantRequired(UserNowInstantRequiredException exception) {
        return buildErrorResponse(
                "USER_NOW_INSTANT_REQUIRED",
                exception.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UserPasswordChangeRequiredException.class)
    public ResponseEntity<ApiErrorResponse> handleUserPasswordChangeRequired(UserPasswordChangeRequiredException exception) {
        return buildErrorResponse(
                "USER_PASSWORD_CHANGE_REQUIRED",
                exception.getMessage(),
                HttpStatus.CONFLICT
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

    @ExceptionHandler(UserAlreadyDisabledException.class)
    public ResponseEntity<ApiErrorResponse> handleUserAlreadyDisabled(
            UserAlreadyDisabledException exception
    ) {
        return buildErrorResponse(
                "USER_ALREADY_DISABLED",
                exception.getMessage(),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(UserNotDisabledException.class)
    public ResponseEntity<ApiErrorResponse> handleUserNotDisabled(
            UserNotDisabledException exception
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
