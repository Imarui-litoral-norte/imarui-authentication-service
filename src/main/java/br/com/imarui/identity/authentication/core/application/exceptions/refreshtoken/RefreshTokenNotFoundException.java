package br.com.imarui.identity.authentication.core.application.exceptions.refreshtoken;

public class RefreshTokenNotFoundException extends RuntimeException {

    public RefreshTokenNotFoundException(Long refreshTokenId) {
        super(
                "Refresh token not found with id: "
                        + refreshTokenId
        );
    }
    public RefreshTokenNotFoundException(String message) {
        super(message);
    }
}