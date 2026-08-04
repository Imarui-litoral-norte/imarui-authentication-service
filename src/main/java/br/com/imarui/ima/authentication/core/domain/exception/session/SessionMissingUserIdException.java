package br.com.imarui.ima.authentication.core.domain.exception.session;

public class SessionMissingUserIdException extends RuntimeException {
    public SessionMissingUserIdException(String message) {
        super(message);
    }
}
