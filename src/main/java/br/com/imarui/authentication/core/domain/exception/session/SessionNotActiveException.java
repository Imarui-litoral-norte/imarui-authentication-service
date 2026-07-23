package br.com.imarui.authentication.core.domain.exception.session;

public class SessionNotActiveException extends RuntimeException {
    public SessionNotActiveException(String message) {
        super(message);
    }
}
