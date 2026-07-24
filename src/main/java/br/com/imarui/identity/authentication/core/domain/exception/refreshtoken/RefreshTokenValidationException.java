package br.com.imarui.identity.authentication.core.domain.exception.refreshtoken;

public class RefreshTokenValidationException
        extends RuntimeException {

    public RefreshTokenValidationException(
            String message
    ) {
        super(message);
    }
}