package br.com.imarui.identity.identity.core.domain.model.tenant;

import br.com.imarui.identity.identity.core.domain.exceptions.tenant.InvalidTenantIdException;

import java.util.Objects;
import java.util.UUID;

public record TenantId(UUID value) {

    public TenantId {
        Objects.requireNonNull(value, "TenantId value must not be null");
    }

    public static TenantId generate() {
        return new TenantId(UUID.randomUUID());
    }

    public static TenantId from(UUID value) {
        return new TenantId(value);
    }

    public static TenantId from(String value) {
        Objects.requireNonNull(value, "TenantId value must not be null");

        try {
            return new TenantId(UUID.fromString(value));
        } catch (IllegalArgumentException exception) {
            throw new InvalidTenantIdException(value, exception);
        }
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
