package br.com.imarui.identity.identity.core.domain.exceptions.tenant;

public class InvalidTenantNameException extends RuntimeException {
    public InvalidTenantNameException(String message) {
        super(message);
    }
}
