package br.com.imarui.identity.authentication.core.application.service.refreshtoken;

import br.com.imarui.identity.authentication.core.application.exceptions.refreshtoken.RefreshTokenNotFoundException;
import br.com.imarui.identity.identity.core.application.exceptions.user.UserIdNotFoundException;
import br.com.imarui.identity.authentication.core.application.result.admin.refresh.AdminRefreshTokenResult;
import br.com.imarui.identity.authentication.core.domain.model.RefreshToken;
import br.com.imarui.identity.authentication.core.repository.RefreshTokenRepository;
import br.com.imarui.identity.authentication.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminRefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final Clock clock;

    @Transactional(readOnly = true)
    public List<AdminRefreshTokenResult> findAll() {
        List<RefreshToken> refreshTokens =
                refreshTokenRepository.findAll();

        return AdminRefreshTokenResult.from(refreshTokens);
    }

    @Transactional(readOnly = true)
    public AdminRefreshTokenResult findById(Long refreshTokenId) {
        RefreshToken refreshToken =
                findRefreshTokenById(refreshTokenId);

        return AdminRefreshTokenResult.from(refreshToken);
    }

    @Transactional(readOnly = true)
    public List<AdminRefreshTokenResult> findByUserId(Long userId) {
        ensureUserExists(userId);

        List<RefreshToken> refreshTokens =
                refreshTokenRepository.findByUserId(userId);

        return AdminRefreshTokenResult.from(refreshTokens);
    }

    @Transactional
    public AdminRefreshTokenResult revoke(Long refreshTokenId) {
        Instant now = Instant.now(clock);

        RefreshToken refreshToken =
                findRefreshTokenByIdForUpdate(refreshTokenId);

        refreshToken.revoke(now);

        RefreshToken savedRefreshToken =
                refreshTokenRepository.save(refreshToken);

        return AdminRefreshTokenResult.from(savedRefreshToken);
    }

    private RefreshToken findRefreshTokenById(Long refreshTokenId) {
        return refreshTokenRepository
                .findById(refreshTokenId)
                .orElseThrow(() ->
                        new RefreshTokenNotFoundException(refreshTokenId)
                );
    }

    private RefreshToken findRefreshTokenByIdForUpdate(
            Long refreshTokenId
    ) {
        return refreshTokenRepository
                .findByIdForUpdate(refreshTokenId)
                .orElseThrow(() ->
                        new RefreshTokenNotFoundException(refreshTokenId)
                );
    }

    private void ensureUserExists(Long userId) {
        if (userRepository.findById(userId).isEmpty()) {
            throw new UserIdNotFoundException(userId);
        }
    }
}
