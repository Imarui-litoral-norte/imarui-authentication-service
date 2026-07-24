package br.com.imarui.identity.authentication.core.application.exceptions.session;

public class SessionNotFoundException extends RuntimeException {
    public SessionNotFoundException(String message) {
        super(message);
    }
    public SessionNotFoundException(Long sessionId) {
        super(
                "Session not found with id: "
                        + sessionId
        );
    }
}
