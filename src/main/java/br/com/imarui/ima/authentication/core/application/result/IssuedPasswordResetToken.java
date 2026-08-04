package br.com.imarui.ima.authentication.core.application.result;

public record IssuedPasswordResetToken(
        String rawToken,
        String tokenHash,
        String resetLink
) {
}
