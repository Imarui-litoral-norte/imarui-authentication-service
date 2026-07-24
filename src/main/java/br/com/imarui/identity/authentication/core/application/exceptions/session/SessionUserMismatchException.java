package br.com.imarui.identity.authentication.core.application.exceptions.session;

public class SessionUserMismatchException extends RuntimeException {
    public SessionUserMismatchException(String message) {
        super(message);
    }
}
