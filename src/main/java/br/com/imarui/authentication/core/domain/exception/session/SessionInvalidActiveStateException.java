package br.com.imarui.authentication.core.domain.exception.session;

public class SessionInvalidActiveStateException extends RuntimeException {
    public SessionInvalidActiveStateException(String message) {
        super(message);
    }
}
