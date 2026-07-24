package br.com.imarui.identity.identity.core.application.exceptions.user;

public class UserCpfNotFoundException extends RuntimeException {

    public UserCpfNotFoundException(String message) {
        super(message);
    }
}
