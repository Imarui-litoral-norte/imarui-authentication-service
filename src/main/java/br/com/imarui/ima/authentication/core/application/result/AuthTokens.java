package br.com.imarui.ima.authentication.core.application.result;

public record AuthTokens(
        String refreshToken,
        String accessToken,
        long expiresInSeconds
) {}