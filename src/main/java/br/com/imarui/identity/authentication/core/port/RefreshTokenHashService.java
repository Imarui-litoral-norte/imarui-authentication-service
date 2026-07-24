package br.com.imarui.identity.authentication.core.port;


public interface RefreshTokenHashService {
    String hash(String rawToken);
}
