package br.com.imarui.ima.authentication.core.application.exceptions.refreshtoken;

public class RefreshTokenInvalidException extends RuntimeException {

    public RefreshTokenInvalidException() {
        super("Refresh token is invalid or expired.");
    }
}