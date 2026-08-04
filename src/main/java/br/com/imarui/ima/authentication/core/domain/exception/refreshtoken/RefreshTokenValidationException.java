package br.com.imarui.ima.authentication.core.domain.exception.refreshtoken;

public class RefreshTokenValidationException
        extends RuntimeException {

    public RefreshTokenValidationException(
            String message
    ) {
        super(message);
    }
}