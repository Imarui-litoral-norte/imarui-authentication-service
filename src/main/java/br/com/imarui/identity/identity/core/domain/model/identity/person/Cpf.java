package br.com.imarui.identity.identity.core.domain.model.identity.person;

import br.com.imarui.identity.identity.core.domain.exception.identity.InvalidCpfException;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.regex.Pattern;

public record Cpf(String value) {

    private static final Pattern ALLOWED_INPUT =
            Pattern.compile("^[0-9.\\-\\s]+$");

    public Cpf {
        Objects.requireNonNull(value, "Cpf value cannot be null");

        if (!ALLOWED_INPUT.matcher(value).matches()) {
            throw new InvalidCpfException(value);
        }

        value = normalize(value);

        if (!isValid(value)) {
            throw new InvalidCpfException(value);
        }
    }

    public static Cpf from(@NotNull String value) {
        return new Cpf(value);
    }

    private static String normalize(String value) {
        return value.replaceAll("\\D", "");
    }

    private static boolean isValid(String value) {
        if (value.length() != 11 || allDigitsAreEqual(value)) {
            return false;
        }

        int firstDigit = calculateDigit(value, 9, 10);
        int secondDigit = calculateDigit(value, 10, 11);

        return firstDigit == Character.digit(value.charAt(9), 10)
                && secondDigit == Character.digit(value.charAt(10), 10);
    }

    private static int calculateDigit(
            String value,
            int length,
            int initialWeight
    ) {
        int sum = 0;

        for (int index = 0; index < length; index++) {
            sum += Character.digit(value.charAt(index), 10)
                    * (initialWeight - index);
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
