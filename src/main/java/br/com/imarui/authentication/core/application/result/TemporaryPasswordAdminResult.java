package br.com.imarui.authentication.core.application.result;

import br.com.imarui.authentication.core.domain.model.PasswordRecoveryRequest;

import java.util.Objects;

public record TemporaryPasswordAdminResult(
        String temporaryPassword,
        PasswordRecoveryRequest recoveryRequest
) {

    public TemporaryPasswordAdminResult {
        if (temporaryPassword == null || temporaryPassword.isBlank()) {
            throw new IllegalArgumentException("temporaryPassword cannot be null or blank");
        }

        Objects.requireNonNull(recoveryRequest, "recoveryRequest cannot be null");
    }
}