package br.com.imarui.identity.identity.core.domain.model.tenant;

import br.com.imarui.identity.identity.core.domain.exception.tenant.InvalidTenantNameException;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public record TenantName(@NotNull String value) {

    private static final int MAX_LENGTH = 150;

    public TenantName {
        Objects.requireNonNull(value, "TenantName value must not be null.");

        value = value.trim();

        if (value.isBlank()) {
            throw new InvalidTenantNameException(
                    "TenantName value must not be blank."
            );
        }

        if (value.length() > MAX_LENGTH) {
            throw new InvalidTenantNameException(
                    "TenantName value must not exceed " + MAX_LENGTH + " characters."
            );
        }
    }

    public static @NotNull TenantName from(@NotNull String value) {
        return new TenantName(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
