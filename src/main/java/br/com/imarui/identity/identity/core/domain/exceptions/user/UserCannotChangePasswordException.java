package br.com.imarui.identity.identity.core.domain.exceptions.user;

public class UserCannotChangePasswordException extends RuntimeException {
    public UserCannotChangePasswordException(String message) {
        super(message);
    }
}
