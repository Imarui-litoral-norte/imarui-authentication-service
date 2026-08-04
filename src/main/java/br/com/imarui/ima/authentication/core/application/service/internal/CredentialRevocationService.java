package br.com.imarui.ima.authentication.core.application.service.internal;

import br.com.imarui.ima.authentication.core.repository.PasswordChangeChallengeRepository;
import br.com.imarui.ima.authentication.core.repository.RefreshTokenRepository;
import br.com.imarui.ima.authentication.core.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class CredentialRevocationService {

    private final SessionRepository sessionRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordChangeChallengeRepository passwordChangeChallengeRepository;

    public void revokeAllForUser(Long userId, Instant now) {
        refreshTokenRepository.revokeActiveByUserId(userId);
        sessionRepository.revokeActiveByUserId(userId);
        passwordChangeChallengeRepository.invalidateActiveByUserId(userId, now);
    }
}
