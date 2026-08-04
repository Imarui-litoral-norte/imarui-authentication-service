package br.com.imarui.ima.identity.core.domain.exception.identity;

public class InvalidIdentityEmailException extends RuntimeException {
    public InvalidIdentityEmailException(String message) {
        super(message);
    }
}
