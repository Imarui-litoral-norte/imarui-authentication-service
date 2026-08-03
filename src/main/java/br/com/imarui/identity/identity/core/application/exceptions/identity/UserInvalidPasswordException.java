package br.com.imarui.identity.identity.core.application.exceptions.identity;

public class UserInvalidPasswordException extends RuntimeException {

    public UserInvalidPasswordException(Long userId) {
        super("Invalid password for user id: " + userId);
    }
}
