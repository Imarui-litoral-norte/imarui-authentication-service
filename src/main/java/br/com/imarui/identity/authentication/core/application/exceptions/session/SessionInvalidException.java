package br.com.imarui.identity.authentication.core.application.exceptions.session;

public class SessionInvalidException extends RuntimeException {
    public SessionInvalidException(String message) {
        super(message);
    }
}
