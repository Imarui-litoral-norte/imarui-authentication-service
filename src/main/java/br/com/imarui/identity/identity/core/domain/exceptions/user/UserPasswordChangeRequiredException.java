package br.com.imarui.identity.identity.core.domain.exceptions.user;

public class UserPasswordChangeRequiredException extends RuntimeException {
    public UserPasswordChangeRequiredException(String message) {
        super(message);
    }
}
