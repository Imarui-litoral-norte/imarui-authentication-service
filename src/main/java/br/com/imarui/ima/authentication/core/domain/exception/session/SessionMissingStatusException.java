package br.com.imarui.ima.authentication.core.domain.exception.session;

public class SessionMissingStatusException extends RuntimeException {
    public SessionMissingStatusException(String message) {
        super(message);
    }
}
