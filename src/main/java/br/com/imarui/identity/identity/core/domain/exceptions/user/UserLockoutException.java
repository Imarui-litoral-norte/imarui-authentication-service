package br.com.imarui.identity.identity.core.domain.exceptions.user;

public class UserLockoutException extends RuntimeException {
    public UserLockoutException(String message) {
        super(message);
    }
}
