package br.com.imarui.identity.authentication.core.application.exceptions.tokenhash;

public class TokenNotFoundException extends RuntimeException {
    public TokenNotFoundException(String message) {
        super(message);
    }
}
