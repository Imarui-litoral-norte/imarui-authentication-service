package br.com.imarui.identity.identity.core.application.exceptions.identity;

public class UserInvalidCredentialsException extends RuntimeException {

  private static final String DEFAULT_MESSAGE = "Invalid CPF or password.";

  public UserInvalidCredentialsException() {
    super(DEFAULT_MESSAGE);
  }
}