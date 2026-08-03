package br.com.imarui.identity.identity.core.domain.exceptions.identity;

public final class InvalidCnpjException extends RuntimeException {

    public InvalidCnpjException(String value) {
        super("Invalid CNPJ value: " + value);
    }
}
