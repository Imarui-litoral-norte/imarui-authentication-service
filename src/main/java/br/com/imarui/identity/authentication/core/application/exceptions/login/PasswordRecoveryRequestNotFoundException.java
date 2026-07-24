package br.com.imarui.identity.authentication.core.application.exceptions.login;

public class PasswordRecoveryRequestNotFoundException extends RuntimeException {
    public PasswordRecoveryRequestNotFoundException(String message) {
        super(message);
    }

    public PasswordRecoveryRequestNotFoundException(Long requestId) {
        super(
                "Password recovery request not found with id: "
                        + requestId
        );
    }
}
