package br.com.imarui.ima.authentication.core.application.result;

public record IssuedTemporaryPassword(
        String rawPassword,
        String passwordHash
) {
}
