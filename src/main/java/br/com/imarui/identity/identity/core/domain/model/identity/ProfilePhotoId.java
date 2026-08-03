package br.com.imarui.identity.identity.core.domain.model.identity;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

public record ProfilePhotoId(@NotNull UUID value) {

    public ProfilePhotoId {
        Objects.requireNonNull(
                value,
                "ProfilePhotoId value must not be null."
        );
    }

    public static @NotNull ProfilePhotoId generate() {
        return new ProfilePhotoId(UUID.randomUUID());
    }

    public static @NotNull ProfilePhotoId from(@NotNull UUID value) {
        return new ProfilePhotoId(value);
    }

    public static @NotNull ProfilePhotoId from(@NotNull String value) {
        Objects.requireNonNull(
                value,
                "ProfilePhotoId value must not be null."
        );

        return from(UUID.fromString(value));
    }
}