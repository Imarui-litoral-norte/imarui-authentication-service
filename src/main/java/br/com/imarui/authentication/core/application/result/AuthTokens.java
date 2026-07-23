package br.com.imarui.authentication.core.application.result;

public record AuthTokens(
        String refreshToken,
        String accessToken,
        long expiresInSeconds
) {}