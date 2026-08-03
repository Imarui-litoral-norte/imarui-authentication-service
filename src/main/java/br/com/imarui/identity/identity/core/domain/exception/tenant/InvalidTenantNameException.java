package br.com.imarui.identity.identity.core.domain.exception.tenant;

public class InvalidTenantNameException extends RuntimeException {
    public InvalidTenantNameException(String message) {
        super(message);
    }
}
