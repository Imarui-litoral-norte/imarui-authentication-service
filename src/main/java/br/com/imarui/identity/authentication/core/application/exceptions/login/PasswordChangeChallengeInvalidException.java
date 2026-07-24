package br.com.imarui.identity.authentication.core.application.exceptions.login;

public class PasswordChangeChallengeInvalidException extends RuntimeException {
    public PasswordChangeChallengeInvalidException(String message) {
        super(message);
    }
}
