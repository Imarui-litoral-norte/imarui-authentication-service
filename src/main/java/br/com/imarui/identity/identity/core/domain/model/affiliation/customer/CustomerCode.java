package br.com.imarui.identity.identity.core.domain.model.affiliation.customer;

import br.com.imarui.identity.identity.core.domain.exceptions.affiliation.InvalidCustomerCodeException;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public record CustomerCode(String value) {

    public CustomerCode {
        Objects.requireNonNull(
                value,
                "CustomerCode value cannot be null"
        );

        value = value.trim();

        if (value.isBlank()) {
            throw new InvalidCustomerCodeException(
                    "CustomerCode value cannot be blank."
            );
        }
    }

    public static CustomerCode from(@NotNull String value) {
        return new CustomerCode(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
