package br.com.imarui.authentication.core.application.result;

public record IssuedTemporaryPassword(
        String rawPassword,
        String passwordHash
) {
}
