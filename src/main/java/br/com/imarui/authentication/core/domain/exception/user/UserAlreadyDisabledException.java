package br.com.imarui.authentication.core.domain.exception.user;

public class UserAlreadyDisabledException
        extends RuntimeException {

  public UserAlreadyDisabledException(Long userId) {
    super(
            "User with id " + userId
                    + " is already disabled."
    );
  }
}