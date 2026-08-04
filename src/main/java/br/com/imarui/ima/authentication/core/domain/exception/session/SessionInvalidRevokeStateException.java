package br.com.imarui.ima.authentication.core.domain.exception.session;

public class SessionInvalidRevokeStateException extends RuntimeException {
    public SessionInvalidRevokeStateException(String message) {
        super(message);
    }
}
