package br.com.imarui.identity.authentication.core.domain.exception.session;

public class SessionMissingCreatedAtException extends RuntimeException {
    public SessionMissingCreatedAtException(String message) {
        super(message);
    }
}
