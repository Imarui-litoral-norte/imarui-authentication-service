package br.com.imarui.identity.identity.core.application.exception.identity;

public class UserInvalidPasswordException extends RuntimeException {

    public UserInvalidPasswordException(Long userId) {
        super("Invalid password for user id: " + userId);
    }
}
