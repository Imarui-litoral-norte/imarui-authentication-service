package br.com.imarui.ima.identity.core.domain.exception.identity;

public class IdentityAlreadyActiveException extends RuntimeException {
    public IdentityAlreadyActiveException(String message) {
        super(message);
    }
}
