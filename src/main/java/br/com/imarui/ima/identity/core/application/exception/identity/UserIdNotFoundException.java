package br.com.imarui.ima.identity.core.application.exception.identity;

public class UserIdNotFoundException extends RuntimeException {

    public UserIdNotFoundException(String message) {
        super(message);
    }

    public UserIdNotFoundException(Long userId) {
        super(
                "User not found with id: "
                        + userId
        );
    }
}