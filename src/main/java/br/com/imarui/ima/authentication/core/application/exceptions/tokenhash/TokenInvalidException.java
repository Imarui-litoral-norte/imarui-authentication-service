package br.com.imarui.ima.authentication.core.application.exceptions.tokenhash;

public class TokenInvalidException extends RuntimeException {
    public TokenInvalidException(String message) {
        super(message);
    }
}
