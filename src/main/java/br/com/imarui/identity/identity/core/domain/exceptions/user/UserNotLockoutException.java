package br.com.imarui.identity.identity.core.domain.exceptions.user;

public class UserNotLockoutException extends RuntimeException {
    public UserNotLockoutException(String message) {
        super(message);
    }
}
