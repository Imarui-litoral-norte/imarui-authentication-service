package br.com.imarui.authentication.core.port;


public interface RefreshTokenHashService {
    String hash(String rawToken);
}
