package br.com.imarui.ima.authentication.core.domain.exception.passwordrecovery;

public class PasswordRecoveryRequestStateException
        extends RuntimeException {

    public PasswordRecoveryRequestStateException(
            String message
    ) {
        super(message);
    }
}