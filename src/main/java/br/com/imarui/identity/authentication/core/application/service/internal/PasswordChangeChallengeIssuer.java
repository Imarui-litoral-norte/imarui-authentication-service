package br.com.imarui.identity.authentication.core.application.service.internal;

import br.com.imarui.identity.authentication.core.application.result.login.PasswordChangeRequiredLoginResult;
import br.com.imarui.identity.authentication.core.domain.model.PasswordChangeChallenge;
import br.com.imarui.identity.authentication.core.port.ApplicationTimeProperties;
import br.com.imarui.identity.authentication.core.port.RefreshTokenGenerator;
import br.com.imarui.identity.authentication.core.port.RefreshTokenHashService;
import br.com.imarui.identity.authentication.core.repository.PasswordChangeChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class PasswordChangeChallengeIssuer {

    private final PasswordChangeChallengeRepository challengeRepository;
    private final RefreshTokenGenerator tokenGenerator;
    private final RefreshTokenHashService tokenHashService;
    private final ApplicationTimeProperties timeProperties;

    public PasswordChangeRequiredLoginResult issue(Long userId, Instant now) {
        challengeRepository.invalidateActiveByUserId(userId, now);
        String rawToken = tokenGenerator.generate();
        Instant expiresAt = now.plus(timeProperties.passwordChangeChallengeTtl());
        challengeRepository.save(PasswordChangeChallenge.create(
                userId,
                tokenHashService.hash(rawToken),
                now,
                expiresAt
        ));
        return new PasswordChangeRequiredLoginResult(rawToken, expiresAt);
    }
}
