package br.com.imarui.identity.identity.core.domain.model.affiliation;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

public record AffiliationId(@NotNull UUID value) {

    public AffiliationId{
        Objects.requireNonNull(
                value,
                "AffiliationId value must not be null."
        );
    }

    public static @NotNull AffiliationId generate(){
        return new AffiliationId(UUID.randomUUID());
    }
    public static @NotNull AffiliationId from(@NotNull UUID value) {
        return new AffiliationId(value);
    }

    public static @NotNull AffiliationId from(@NotNull String value) {
        Objects.requireNonNull(
                value,
                "AffiliationId value must not be null."
        );

        return from(UUID.fromString(value));
    }
}


