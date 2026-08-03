package br.com.imarui.identity.identity.core.domain.model.identity.service;

import br.com.imarui.identity.identity.core.domain.exception.identity.InvalidServiceDescriptionException;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public record ServiceDescription(String value) {

    private static final int MAX_LENGTH = 500;

    public ServiceDescription {
        Objects.requireNonNull(
                value,
                "ServiceDescription value cannot be null"
        );

        value = value.trim();

        if (value.isBlank()) {
            throw new InvalidServiceDescriptionException(
                    "ServiceDescription value cannot be blank."
            );
        }

        if (value.length() > MAX_LENGTH) {
            throw new InvalidServiceDescriptionException(
                    "ServiceDescription value cannot exceed "
                            + MAX_LENGTH
                            + " characters."
            );
        }
    }

    public static ServiceDescription from(@NotNull String value) {
        return new ServiceDescription(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
