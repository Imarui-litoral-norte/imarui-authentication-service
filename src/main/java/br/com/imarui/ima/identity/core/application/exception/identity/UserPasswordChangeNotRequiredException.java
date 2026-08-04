package br.com.imarui.ima.identity.core.application.exception.identity;

public class UserPasswordChangeNotRequiredException extends RuntimeException {

    public UserPasswordChangeNotRequiredException() {
        super("User does not have a pending password change.");
    }
}