package br.com.imarui.ima.authentication.core.domain.exception.refreshtoken;

public class RefreshTokenStateException
        extends RuntimeException {

    public RefreshTokenStateException(
            String message
    ) {
        super(message);
    }
}