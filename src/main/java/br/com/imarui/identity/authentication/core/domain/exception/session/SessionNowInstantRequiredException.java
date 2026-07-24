package br.com.imarui.identity.authentication.core.domain.exception.session;

public class SessionNowInstantRequiredException extends RuntimeException {
    public SessionNowInstantRequiredException(String message) {
        super(message);
    }
}
