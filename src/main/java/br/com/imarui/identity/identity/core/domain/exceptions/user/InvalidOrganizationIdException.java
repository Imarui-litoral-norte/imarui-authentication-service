package br.com.imarui.identity.identity.core.domain.exceptions.user;

public class InvalidOrganizationIdException extends RuntimeException {

    public InvalidOrganizationIdException(String value, Throwable cause) {
        super("Invalid organization id: " + value, cause);
    }
}
