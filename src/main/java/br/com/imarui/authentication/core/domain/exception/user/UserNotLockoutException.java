package br.com.imarui.authentication.core.domain.exception.user;

public class UserNotLockoutException extends RuntimeException {
    public UserNotLockoutException(String message) {
        super(message);
    }
}
