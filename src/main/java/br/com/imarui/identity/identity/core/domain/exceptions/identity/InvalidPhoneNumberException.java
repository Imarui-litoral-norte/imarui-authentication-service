package br.com.imarui.identity.identity.core.domain.exceptions.identity;

public class InvalidPhoneNumberException extends RuntimeException {
    public InvalidPhoneNumberException(String message) {
        super(message);
    }
}
