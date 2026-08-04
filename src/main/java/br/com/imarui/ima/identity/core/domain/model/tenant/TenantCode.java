package br.com.imarui.ima.identity.core.domain.model.tenant;

import br.com.imarui.ima.identity.core.domain.exception.tenant.InvalidTenantCodeException;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;

public record TenantCode(String value) {

    private static final int MIN_LENGTH = 3;
    private static final int MAX_LENGTH = 50;

    private static final Pattern VALID_PATTERN =
            Pattern.compile("^[A-Z][A-Z0-9_]*$");

    public TenantCode {
        Objects.requireNonNull(value, "value cannot be null");

        value = normalize(value);

        validate(value);
    }

    @NotNull
    public static TenantCode from(@NotNull String value) {
        Objects.requireNonNull(value, "value cannot be null");

        return new TenantCode(value);
    }

    private static String normalize(String value) {
        return value
                .trim()
                .toUpperCase(Locale.ROOT);
    }

    private static void validate(String value) {
        if (value.isBlank()) {
            throw InvalidTenantCodeException.blank();
        }

        if (value.length() < MIN_LENGTH) {
            throw InvalidTenantCodeException.tooShort(MIN_LENGTH);
        }

        if (value.length() > MAX_LENGTH) {
            throw InvalidTenantCodeException.tooLong(MAX_LENGTH);
        }

        if (!VALID_PATTERN.matcher(value).matches()) {
            throw InvalidTenantCodeException.invalidFormat();
        }
    }

    @Override
    public String toString() {
        return value;
    }
}