package br.com.imarui.ima.authentication.core.port;


public interface RefreshTokenHashService {
    String hash(String rawToken);
}
