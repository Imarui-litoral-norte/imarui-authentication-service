package br.com.imarui.ima.authentication.core.domain.model;

import java.time.Instant;

public record IssuedAccessToken(
        String token,
        Instant expiresAt
) {}
