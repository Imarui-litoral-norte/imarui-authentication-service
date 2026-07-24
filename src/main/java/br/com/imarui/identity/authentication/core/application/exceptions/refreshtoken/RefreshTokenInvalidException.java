package br.com.imarui.identity.authentication.core.application.exceptions.refreshtoken;

public class RefreshTokenInvalidException extends RuntimeException {

    public RefreshTokenInvalidException() {
        super("Refresh token is invalid or expired.");
    }
}