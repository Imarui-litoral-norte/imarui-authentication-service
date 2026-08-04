package br.com.imarui.ima.authentication.core.domain.exception.session;

public class SessionInvalidTtlValueException extends RuntimeException {
    public SessionInvalidTtlValueException(String message) {
        super(message);
    }
}
