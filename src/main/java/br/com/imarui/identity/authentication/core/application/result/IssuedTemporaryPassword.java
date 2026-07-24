package br.com.imarui.identity.authentication.core.application.result;

public record IssuedTemporaryPassword(
        String rawPassword,
        String passwordHash
) {
}
