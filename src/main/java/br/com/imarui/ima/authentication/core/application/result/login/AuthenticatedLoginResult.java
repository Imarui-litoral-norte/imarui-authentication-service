package br.com.imarui.ima.authentication.core.application.result.login;
import br.com.imarui.ima.authentication.core.application.result.AuthTokens;

import java.util.Objects;

public record AuthenticatedLoginResult(
        AuthTokens tokens
) implements LoginResult {

    public AuthenticatedLoginResult {
        Objects.requireNonNull(tokens, "tokens cannot be null");
    }
}