package br.com.imarui.authentication.core.application.result;

import br.com.imarui.authentication.core.domain.model.RefreshToken;

public record IssuedRefreshToken(
        RefreshToken refreshToken,
        String rawToken
) {}