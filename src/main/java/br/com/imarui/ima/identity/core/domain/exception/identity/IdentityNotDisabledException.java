package br.com.imarui.ima.identity.core.domain.exception.identity;

public class IdentityNotDisabledException extends RuntimeException {

    public IdentityNotDisabledException(String userId) {
        super(
                "User with id " + userId
                        + " is not disabled."
        );
    }
}