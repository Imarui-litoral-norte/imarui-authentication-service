package br.com.imarui.ima.authentication.core.port;

public interface NotificationPort {
    void sendPasswordResetLink(String email, String resetLink);
}
