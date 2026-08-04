package br.com.imarui.ima.authentication.core.application.result;

import br.com.imarui.ima.authentication.core.domain.model.RefreshToken;

public record IssuedRefreshToken(
        RefreshToken refreshToken,
        String rawToken
) {}