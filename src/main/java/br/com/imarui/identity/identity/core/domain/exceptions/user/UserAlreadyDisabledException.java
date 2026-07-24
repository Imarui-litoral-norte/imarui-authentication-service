package br.com.imarui.identity.identity.core.domain.exceptions.user;

public class UserAlreadyDisabledException
        extends RuntimeException {

  public UserAlreadyDisabledException(Long userId) {
    super(
            "User with id " + userId
                    + " is already disabled."
    );
  }
}