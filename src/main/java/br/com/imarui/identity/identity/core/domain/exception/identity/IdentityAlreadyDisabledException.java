package br.com.imarui.identity.identity.core.domain.exception.identity;

public class IdentityAlreadyDisabledException
        extends RuntimeException {

  public IdentityAlreadyDisabledException(String userId) {
    super(
            "User with id " + userId
                    + " is already disabled."
    );
  }
}