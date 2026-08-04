package br.com.imarui.ima.authentication.core.application.result;

public record MeResult(
        Long userId,
        String userName,
        Long sessionId,
        boolean sessionActive,
        long expiresInSeconds
) {
}