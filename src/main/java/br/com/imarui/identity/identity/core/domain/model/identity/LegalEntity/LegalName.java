package br.com.imarui.identity.identity.core.domain.model.identity.LegalEntity;

import br.com.imarui.identity.identity.core.domain.exception.identity.InvalidLegalNameException;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public record LegalName(String value) {

    private static final int MAX_LENGTH = 150;

    public LegalName {
        Objects.requireNonNull(
                value,
                "LegalName value cannot be null"
        );

        value = value.trim();

        if (value.isBlank()) {
            throw new InvalidLegalNameException(
                    "LegalName value cannot be blank."
            );
        }

        if (value.length() > MAX_LENGTH) {
            throw new InvalidLegalNameException(
                    "LegalName value cannot exceed "
                            + MAX_LENGTH
                            + " characters."
            );
        }
    }

    public static LegalName from(@NotNull String value) {
        return new LegalName(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
