package br.com.imarui.ima.identity.core.domain.exception.identity;

public final class InvalidCnpjException extends RuntimeException {

    public InvalidCnpjException(String value) {
        super("Invalid CNPJ value: " + value);
    }
}
