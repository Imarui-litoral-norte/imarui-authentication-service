package br.com.imarui.ima.identity.core.domain.exception.identity;

public class InvalidIdentityStateException extends RuntimeException {
    public InvalidIdentityStateException(String message) {
        super(message);
    }
}
