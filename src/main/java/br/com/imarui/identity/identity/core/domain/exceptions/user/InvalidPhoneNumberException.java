package br.com.imarui.identity.identity.core.domain.exceptions.user;

public class InvalidPhoneNumberException extends RuntimeException {
    public InvalidPhoneNumberException(String message) {
        super(message);
    }
}
