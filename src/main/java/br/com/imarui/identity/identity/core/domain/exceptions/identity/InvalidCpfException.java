package br.com.imarui.identity.identity.core.domain.exceptions.identity;

public final class InvalidCpfException extends RuntimeException {

    public InvalidCpfException(String value) {
        super("Invalid CPF value: " + value);
    }
}
