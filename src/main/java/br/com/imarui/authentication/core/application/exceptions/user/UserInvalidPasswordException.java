package br.com.imarui.authentication.core.application.exceptions.user;

public class UserInvalidPasswordException extends RuntimeException {

    public UserInvalidPasswordException(Long userId) {
        super("Invalid password for user id: " + userId);
    }
}
