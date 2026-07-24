package br.com.imarui.identity.identity.core.application.exceptions.user;

public class UserDisabledException extends RuntimeException {
    public UserDisabledException(String message) {
        super(message);
    }
}
