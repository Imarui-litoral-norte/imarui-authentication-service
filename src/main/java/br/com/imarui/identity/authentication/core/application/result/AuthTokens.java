package br.com.imarui.identity.authentication.core.application.result;

public record AuthTokens(
        String refreshToken,
        String accessToken,
        long expiresInSeconds
) {}