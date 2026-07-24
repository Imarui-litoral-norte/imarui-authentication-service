package br.com.imarui.identity.authentication.core.domain.exception.session;

public class SessionMissingStatusException extends RuntimeException {
    public SessionMissingStatusException(String message) {
        super(message);
    }
}
