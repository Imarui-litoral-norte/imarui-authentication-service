package br.com.imarui.identity.identity.core.domain.exceptions.user;

public class UserNewHashRequiredException extends RuntimeException {
    public UserNewHashRequiredException(String message) {
        super(message);
    }
}
