package br.com.imarui.identity.authentication.core.domain.exception.session;

public class SessionInvalidRevokeStateException extends RuntimeException {
    public SessionInvalidRevokeStateException(String message) {
        super(message);
    }
}
