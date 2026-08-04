package br.com.imarui.ima.authentication.core.repository;

import br.com.imarui.ima.authentication.core.domain.model.PasswordChangeChallenge;

import java.time.Instant;
import java.util.Optional;

public interface PasswordChangeChallengeRepository {

    PasswordChangeChallenge save(
            PasswordChangeChallenge challenge
    );

    Optional<PasswordChangeChallenge> findByTokenHash(
            String tokenHash
    );

    Optional<Long> findUserIdByTokenHash(
            String tokenHash
    );

    Optional<PasswordChangeChallenge> findByTokenHashForUpdate(
            String tokenHash
    );

    void invalidateActiveByUserId(
            Long userId,
            Instant now
    );
}
