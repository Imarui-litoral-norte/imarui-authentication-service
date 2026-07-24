package br.com.imarui.identity.identity.core.domain.exceptions.user;

public class UserIdRequiredException extends RuntimeException {
    public UserIdRequiredException(String message) {
        super(message);
    }
}
