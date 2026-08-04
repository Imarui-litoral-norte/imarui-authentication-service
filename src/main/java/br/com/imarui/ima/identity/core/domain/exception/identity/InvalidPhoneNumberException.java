package br.com.imarui.ima.identity.core.domain.exception.identity;

public class InvalidPhoneNumberException extends RuntimeException {
    public InvalidPhoneNumberException(String message) {
        super(message);
    }
}
