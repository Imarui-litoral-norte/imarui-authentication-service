package br.com.imarui.identity.identity.core.domain.model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

public record UserId(@NotNull UUID value) {

    public UserId {
        Objects.requireNonNull(value, "UserId value must not be null.");
    }

    @Contract(" -> new")
    public static @NotNull UserId generate() {
        return new UserId(UUID.randomUUID());
    }

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull UserId from(@NotNull UUID value) {
        return new UserId(value);
    }

    @Contract(value = "_ -> new", pure = true)
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