package br.com.imarui.identity.identity.core.application.exceptions.identity;

public class UserDisabledException extends RuntimeException {
    public UserDisabledException(String message) {
        super(message);
    }
}
