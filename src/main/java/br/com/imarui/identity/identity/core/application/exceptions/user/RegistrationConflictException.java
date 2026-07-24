package br.com.imarui.identity.identity.core.application.exceptions.user;

public class RegistrationConflictException extends RuntimeException {

    public RegistrationConflictException() {
        super("Registration could not be completed with the provided data.");
    }
}
