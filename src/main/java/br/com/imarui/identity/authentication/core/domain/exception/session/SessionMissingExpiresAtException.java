package br.com.imarui.identity.authentication.core.domain.exception.session;

public class SessionMissingExpiresAtException extends RuntimeException {
    public SessionMissingExpiresAtException(String message) {
        super(message);
    }
}
