package br.com.imarui.ima.identity.core.application.exception.identity;

public class RegistrationConflictException extends RuntimeException {

    public RegistrationConflictException() {
        super("Registration could not be completed with the provided data.");
    }
}
