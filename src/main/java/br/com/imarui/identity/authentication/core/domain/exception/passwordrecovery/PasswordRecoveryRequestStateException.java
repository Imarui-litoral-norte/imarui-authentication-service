package br.com.imarui.identity.authentication.core.domain.exception.passwordrecovery;

public class PasswordRecoveryRequestStateException
        extends RuntimeException {

    public PasswordRecoveryRequestStateException(
            String message
    ) {
        super(message);
    }
}