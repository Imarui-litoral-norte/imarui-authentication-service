package br.com.imarui.authentication.core.domain.exception.session;

public class SessionMissingUserIdException extends RuntimeException {
    public SessionMissingUserIdException(String message) {
        super(message);
    }
}
