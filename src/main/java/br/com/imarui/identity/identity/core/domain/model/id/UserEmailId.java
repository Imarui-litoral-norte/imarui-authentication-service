package br.com.imarui.identity.identity.core.domain.model.id;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

public record UserEmailId(@NotNull UUID value) {

    public UserEmailId {
        Objects.requireNonNull(
                value,
                "UserEmailId value must not be null."
        );
    }

    public static @NotNull UserEmailId generate() {
        return new UserEmailId(UUID.randomUUID());
    }

    public static @NotNull UserEmailId from(@NotNull UUID value) {
        return new UserEmailId(value);
    }

    public static @NotNull UserEmailId from(@NotNull String value) {
        Objects.requireNonNull(
                value,
                "UserEmailId value must not be null."
        );

        try {
            return from(UUID.fromString(value));
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(
                    "Invalid UserEmailId: " + value,
                    exception
            );
        }
    }

    @Override
    public String toString() {
        return value.toString();
    }
}