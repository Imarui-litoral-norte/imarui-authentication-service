package br.com.imarui.identity.identity.core.domain.model;

import br.com.imarui.identity.identity.core.domain.exceptions.user.InvalidEmailException;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.regex.Pattern;

public record Email(@NotNull String value) {

    private static final int MAX_LENGTH = 254;

    private static final Pattern PATTERN = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$",
            Pattern.CASE_INSENSITIVE
    );

    public Email {
        if (value == null) {
            throw new InvalidEmailException(
                    "Email value must not be null."
            );
        }

        value = value.trim().toLowerCase(Locale.ROOT);

        if (value.isBlank()) {
            throw new InvalidEmailException(
                    "Email value must not be blank."
            );
        }

        if (value.length() > MAX_LENGTH) {
            throw new InvalidEmailException(
                    "Email value must not exceed " + MAX_LENGTH + " characters."
            );
        }

        if (!PATTERN.matcher(value).matches()) {
            throw new InvalidEmailException(
                    "Email value has an invalid format."
            );
        }
    }

    public static @NotNull Email from(@NotNull String value) {
        return new Email(value);
    }

    @Override
    public String toString() {
        return value;
    }
}