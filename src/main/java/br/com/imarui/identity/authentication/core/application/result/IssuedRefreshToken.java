package br.com.imarui.identity.authentication.core.application.result;

import br.com.imarui.identity.authentication.core.domain.model.RefreshToken;

public record IssuedRefreshToken(
        RefreshToken refreshToken,
        String rawToken
) {}