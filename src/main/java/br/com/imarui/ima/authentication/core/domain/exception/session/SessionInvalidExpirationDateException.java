package br.com.imarui.ima.authentication.core.domain.exception.session;

public class SessionInvalidExpirationDateException extends RuntimeException {
    public SessionInvalidExpirationDateException(String message) {
        super(message);
    }
}
