package br.com.imarui.ima.authentication.core.domain.exception.session;

public class SessionMissingExpiresAtException extends RuntimeException {
    public SessionMissingExpiresAtException(String message) {
        super(message);
    }
}
