package br.com.imarui.ima.authentication.core.domain.exception.session;

public class SessionMissingTtlException extends RuntimeException {
    public SessionMissingTtlException(String message) {
        super(message);
    }
}
