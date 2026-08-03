package br.com.imarui.identity.identity.core.domain.exceptions.identity;

public class InvalidIdentityStateException extends RuntimeException {
    public InvalidIdentityStateException(String message) {
        super(message);
    }
}
