package br.com.imarui.ima.identity.core.domain.model.identityapplication;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

public record IdentityApplicationId(@NotNull UUID value) {

    public IdentityApplicationId {
        Objects.requireNonNull(
                value,
                "Identity Application Id value must not be null."
        );
    }

    public static @NotNull IdentityApplicationId generate() {
        return new IdentityApplicationId(UUID.randomUUID());
    }

    public static @NotNull IdentityApplicationId from(@NotNull UUID value) {
        return new IdentityApplicationId(value);
    }

    public static @NotNull IdentityApplicationId from(@NotNull String value) {
        Objects.requireNonNull(
                value,
                "Identity Application Id value must not be null."
        );

        return from(UUID.fromString(value));
    }
}
