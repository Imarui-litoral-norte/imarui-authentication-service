package br.com.imarui.identity.authentication.core.domain.exception.passwordchangechallenge;

public class PasswordChangeChallengeValidationException
        extends RuntimeException {

    public PasswordChangeChallengeValidationException(
            String message
    ) {
        super(message);
    }
}