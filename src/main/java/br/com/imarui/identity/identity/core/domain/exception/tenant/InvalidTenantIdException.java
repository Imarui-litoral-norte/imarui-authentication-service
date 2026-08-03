package br.com.imarui.identity.identity.core.domain.exception.tenant;

public class InvalidTenantIdException extends RuntimeException {

    public InvalidTenantIdException(String value, Throwable cause) {
        super("Invalid TenantId id: " + value, cause);
    }
}
