package br.com.imarui.identity.identity.core.domain.exception.tenant;

import lombok.Getter;

@Getter
public final class InvalidTenantIdException extends RuntimeException {

    private final String invalidValue;

    public InvalidTenantIdException(String value, Throwable cause) {
        super("Invalid TenantId id: " + value, cause);
        this.invalidValue = value;
    }
}
