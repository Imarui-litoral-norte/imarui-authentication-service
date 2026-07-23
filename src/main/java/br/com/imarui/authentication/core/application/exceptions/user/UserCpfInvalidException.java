package br.com.imarui.authentication.core.application.exceptions.user;

public class UserCpfInvalidException extends RuntimeException {
    public UserCpfInvalidException(String message) {
        super(message);
    }
}
