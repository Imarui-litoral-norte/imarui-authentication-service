package br.com.imarui.ima.identity.core.domain.exception.identity;

public final class InvalidCpfException extends RuntimeException {

    public InvalidCpfException(String value) {
        super("Invalid CPF value: " + value);
    }
}
