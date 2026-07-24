package br.com.imarui.identity.authentication.core.application.result;

public record IssuedPasswordResetToken(
        String rawToken,
        String tokenHash,
        String resetLink
) {
}
