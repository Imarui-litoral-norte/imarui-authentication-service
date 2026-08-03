package br.com.imarui.identity.identity.core.domain.exceptions.identity;

public class IdentityNotDisabledException extends RuntimeException {

    public IdentityNotDisabledException(String userId) {
        super(
                "User with id " + userId
                        + " is not disabled."
        );
    }
}