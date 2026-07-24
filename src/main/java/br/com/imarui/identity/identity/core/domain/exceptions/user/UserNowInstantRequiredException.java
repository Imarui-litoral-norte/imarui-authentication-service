package br.com.imarui.identity.identity.core.domain.exceptions.user;

public class UserNowInstantRequiredException extends RuntimeException {
    public UserNowInstantRequiredException(String message) {
        super(message);
    }
}
