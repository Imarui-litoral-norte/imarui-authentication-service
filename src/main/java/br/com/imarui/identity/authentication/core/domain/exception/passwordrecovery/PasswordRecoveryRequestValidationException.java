package br.com.imarui.identity.authentication.core.domain.exception.passwordrecovery;

public class PasswordRecoveryRequestValidationException
        extends RuntimeException {

    public PasswordRecoveryRequestValidationException(
            String message
    ) {
        super(message);
    }
}