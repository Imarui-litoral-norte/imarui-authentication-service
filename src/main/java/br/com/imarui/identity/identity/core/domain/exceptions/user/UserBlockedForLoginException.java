package br.com.imarui.identity.identity.core.domain.exceptions.user;

public class UserBlockedForLoginException extends RuntimeException {
    public UserBlockedForLoginException(String message) {
        super(message);
    }
}
