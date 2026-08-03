package br.com.imarui.identity.identity.core.domain.exceptions.identity;

public class IdentityAlreadyActiveException extends RuntimeException {
    public IdentityAlreadyActiveException(String message) {
        super(message);
    }
}
