package br.com.imarui.identity.identity.core.domain.model.identity;

import br.com.imarui.identity.identity.core.domain.exception.identity.InvalidFullNameException;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
public record IdentityFullName(@NotNull String value) {

    private static final int MAX_LENGTH = 150;

    public IdentityFullName {
        Objects.requireNonNull(value, "FullName value must not be null.");

        value = value.trim();

        if (value.isBlank()) {
            throw new InvalidFullNameException(
                    "FullName value must not be blank."
            );
        }

        if (value.length() > MAX_LENGTH) {
            throw new InvalidFullNameException(
                    "FullName value must not exceed " + MAX_LENGTH + " characters."
            );
        }
    }

    public static @NotNull IdentityFullName from(@NotNull String value) {
        return new IdentityFullName(value);
    }

    @Override
    public String toString() {
        return value;
    }
}