package br.com.imarui.identity.identity.core.domain.model.id;

import br.com.imarui.identity.identity.core.domain.exceptions.user.InvalidOrganizationIdException;

import java.util.Objects;
import java.util.UUID;

public record OrganizationId(UUID value) {

    public OrganizationId {
        Objects.requireNonNull(value, "OrganizationId value must not be null");
    }

    public static OrganizationId generate() {
        return new OrganizationId(UUID.randomUUID());
    }

    public static OrganizationId from(UUID value) {
        return new OrganizationId(value);
    }

    public static OrganizationId from(String value) {
        Objects.requireNonNull(value, "OrganizationId value must not be null");

        try {
            return new OrganizationId(UUID.fromString(value));
        } catch (IllegalArgumentException exception) {
            throw new InvalidOrganizationIdException(value, exception);
        }
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
