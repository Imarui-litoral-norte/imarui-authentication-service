package br.com.imarui.identity.identity.core.domain.model.identity.LegalEntity;

import br.com.imarui.identity.identity.core.domain.exception.identity.InvalidCnpjException;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.regex.Pattern;

public record Cnpj(String value) {

    private static final Pattern ALLOWED_INPUT =
            Pattern.compile("^[0-9./\\-\\s]+$");

    private static final int[] FIRST_WEIGHTS =
            {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    private static final int[] SECOND_WEIGHTS =
            {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    public Cnpj {
        Objects.requireNonNull(value, "Cnpj value cannot be null");

        if (!ALLOWED_INPUT.matcher(value).matches()) {
            throw new InvalidCnpjException(value);
        }

        value = normalize(value);

        if (!isValid(value)) {
            throw new InvalidCnpjException(value);
        }
    }

    public static Cnpj from(@NotNull String value) {
        return new Cnpj(value);
    }

    private static String normalize(String value) {
        return value.replaceAll("\\D", "");
    }

    private static boolean isValid(String value) {
        if (value.length() != 14 || allDigitsAreEqual(value)) {
            return false;
        }

        int firstDigit = calculateDigit(value, FIRST_WEIGHTS);
        int secondDigit = calculateDigit(value, SECOND_WEIGHTS);

        return firstDigit == Character.digit(value.charAt(12), 10)
                && secondDigit == Character.digit(value.charAt(13), 10);
    }

    private static int calculateDigit(
            String value,
            int[] weights
    ) {
        int sum = 0;

        for (int index = 0; index < weights.length; index++) {
            sum += Character.digit(value.charAt(index), 10)
                    * weights[index];
        }

        int remainder = sum % 11;

        return remainder < 2 ? 0 : 11 - remainder;
    }

    private static boolean allDigitsAreEqual(String value) {
        char first = value.charAt(0);

        for (int index = 1; index < value.length(); index++) {
            if (value.charAt(index) != first) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return value;
    }
}
