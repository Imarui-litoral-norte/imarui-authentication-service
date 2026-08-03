package br.com.imarui.identity.identity.core.domain.model.identity;

import br.com.imarui.identity.identity.core.domain.exception.identity.InvalidPhoneNumberException;

public record PhoneNumber(String value) {

    public PhoneNumber {
        if (value == null) {
            throw new InvalidPhoneNumberException(
                    "PhoneNumber value must not be null."
            );
        }

        value = normalize(value);

        if (value.length() < 10 || value.length() > 15) {
            throw new InvalidPhoneNumberException(
                    "PhoneNumber value must contain between 10 and 15 digits."
            );
        }
    }

    public static PhoneNumber from(String value) {
        return new PhoneNumber(value);
    }

    private static String normalize(String value) {
        StringBuilder digits = new StringBuilder(value.length());

        for (int index = 0; index < value.length(); index++) {
            char character = value.charAt(index);

            if (Character.isDigit(character)) {
                digits.append(character);
            }
        }

        return digits.toString();
    }

    @Override
    public String toString() {
        return value;
    }
}
