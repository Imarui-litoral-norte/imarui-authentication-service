package br.com.imarui.authentication.core.domain.exception.passwordchangechallenge;

public class PasswordChangeChallengeValidationException
        extends RuntimeException {

    public PasswordChangeChallengeValidationException(
            String message
    ) {
        super(message);
    }
}