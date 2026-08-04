package br.com.imarui.ima.identity.core.domain.exception.identity;

public class IdentityNowInstantRequiredException extends RuntimeException {
    public IdentityNowInstantRequiredException(String message) {
        super(message);
    }
}
