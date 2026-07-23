package br.com.imarui.authentication.core.port;

public interface NotificationPort {
    void sendPasswordResetLink(String email, String resetLink);
}
