package br.com.imarui.ima.identity.core.application.exception.identity;

public class UserCpfNotFoundException extends RuntimeException {

    public UserCpfNotFoundException(String message) {
        super(message);
    }
}
