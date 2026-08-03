package br.com.imarui.identity.identity.core.domain.exception.affiliation;

public final class InvalidCustomerCodeException extends RuntimeException {

    public InvalidCustomerCodeException(String message) {
        super(message);
    }
}
