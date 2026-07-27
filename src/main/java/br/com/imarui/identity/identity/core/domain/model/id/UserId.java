package br.com.imarui.identity.identity.core.domain.model.id;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

public record UserId(@NotNull UUID value) {

    public UserId {
        Objects.requireNonNull(value, "UserId value must not be null.");
    }

    public static @NotNull UserId generate() {
        return new UserId(UUID.randomUUID());
    }

    public static @NotNull UserId from(@NotNull UUID value) {
        return new UserId(value);
    }

    public static @NotNull UserId from(@NotNull String value) {
        Objects.requireNonNull(value, "UserId value must not be null.");

        try {
            return from(UUID.fromString(value));
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(
                    "Invalid UserId: " + value,
                    exception
            );
        }
    }

    @Override
    public String toString() {
        return value.toString();
    }
}