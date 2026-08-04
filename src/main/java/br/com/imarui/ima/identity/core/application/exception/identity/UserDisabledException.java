package br.com.imarui.ima.identity.core.application.exception.identity;

public class UserDisabledException extends RuntimeException {
    public UserDisabledException(String message) {
        super(message);
    }
}
