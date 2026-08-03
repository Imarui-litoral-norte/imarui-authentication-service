package br.com.imarui.identity.identity.core.domain.model.identity.LegalEntity;

import br.com.imarui.identity.identity.core.domain.exceptions.identity.InvalidTradeNameException;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public record TradeName(String value) {

    private static final int MAX_LENGTH = 150;

    public TradeName {
        Objects.requireNonNull(
                value,
                "TradeName value cannot be null"
        );

        value = value.trim();

        if (value.isBlank()) {
            throw new InvalidTradeNameException(
                    "TradeName value cannot be blank."
            );
        }

        if (value.length() > MAX_LENGTH) {
            throw new InvalidTradeNameException(
                    "TradeName value cannot exceed "
                            + MAX_LENGTH
                            + " characters."
            );
        }
    }

    public static TradeName from(@NotNull String value) {
        return new TradeName(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
