package br.com.imarui.authentication.core.application.result;

public record IssuedPasswordResetToken(
        String rawToken,
        String tokenHash,
        String resetLink
) {
}
