package br.com.imarui.identity.identity.core.domain.exceptions.user;

public class UserNotDisabledException extends RuntimeException {

    public UserNotDisabledException(Long userId) {
        super(
                "User with id " + userId
                        + " is not disabled."
        );
    }
}