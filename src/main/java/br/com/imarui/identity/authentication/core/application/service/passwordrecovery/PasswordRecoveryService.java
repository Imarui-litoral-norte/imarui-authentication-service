package br.com.imarui.identity.authentication.core.application.service.passwordrecovery;

import br.com.imarui.identity.authentication.core.application.exceptions.tokenhash.TokenInvalidException;
import br.com.imarui.identity.authentication.core.application.exceptions.tokenhash.TokenNotFoundException;
import br.com.imarui.identity.identity.core.application.exceptions.identity.UserIdNotFoundException;
import br.com.imarui.identity.authentication.core.application.result.IssuedPasswordResetToken;
import br.com.imarui.identity.authentication.core.application.result.PublicPasswordRecoveryResult;
import br.com.imarui.identity.authentication.core.application.service.internal.CredentialRevocationService;
import br.com.imarui.identity.authentication.core.application.service.internal.PasswordRecoveryRequestManager;
import br.com.imarui.identity.authentication.core.application.service.internal.PasswordResetTokenIssuer;
import br.com.imarui.identity.authentication.core.domain.model.PasswordRecoveryRequest;
import br.com.imarui.identity.identity.core.domain.model.identity.Identity;
import br.com.imarui.identity.authentication.core.port.NotificationPort;
import br.com.imarui.identity.authentication.core.port.PasswordHasher;
import br.com.imarui.identity.authentication.core.port.RefreshTokenHashService;
import br.com.imarui.identity.authentication.core.repository.PasswordRecoveryRequestRepository;
import br.com.imarui.identity.identity.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class PasswordRecoveryService {

    private final UserRepository userRepository;
    private final PasswordRecoveryRequestRepository requestRepository;
    private final PasswordRecoveryRequestManager requestManager;
    private final NotificationPort notificationPort;
    private final PasswordResetTokenIssuer tokenIssuer;
    private final RefreshTokenHashService tokenHashService;
    private final PasswordHasher passwordHasher;
    private final CredentialRevocationService revocationService;
    private final Clock clock;

    @Transactional
    public PublicPasswordRecoveryResult request(String cpf) {
        Instant now = Instant.now(clock);
        Identity user = userRepository.findByCpfForUpdate(cpf).orElse(null);
        if (user != null && !user.isDisabled()) {
            requestManager.getOrCreate(user.getId(), now);
        }
        return requestManager.genericResult(now);
    }

    @Transactional
    public PublicPasswordRecoveryResult sendEmailToken(String cpf) {
        Instant now = Instant.now(clock);
        Identity user = userRepository.findByCpfForUpdate(cpf).orElse(null);
        if (user == null || user.isDisabled()
                || user.getEmail() == null || user.getEmail().isBlank()) {
            return requestManager.genericEmailResult(now);
        }
        PasswordRecoveryRequest request = requestManager.getOrCreate(user.getId(), now);
        IssuedPasswordResetToken token = tokenIssuer.issue();
        request.changeToEmailTokenMethod(token.tokenHash(), now);
        requestRepository.save(request);
        notificationPort.sendPasswordResetLink(
                user.getEmail(),
                token.resetLink()
        );
        return requestManager.genericEmailResult(now);
    }

    @Transactional
    public void resetPasswordByToken(String rawToken, String newPassword) {
        Instant now = Instant.now(clock);
        String tokenHash = tokenHashService.hash(rawToken);
        Long userId = requestRepository.findUserIdByTokenHash(tokenHash)
                .orElseThrow(() -> new TokenNotFoundException("Token invalid or expired."));
        Identity user = userRepository.findByIdForUpdate(userId)
                .orElseThrow(() -> new UserIdNotFoundException("User not found with this id."));
        PasswordRecoveryRequest request = requestRepository.findByTokenHashForUpdate(tokenHash)
                .orElseThrow(() -> new TokenNotFoundException("Token invalid or expired."));
        if (!request.getUserId().equals(user.getId())
                || !request.isTokenBased() || !request.isOpen(now)) {
            throw new TokenInvalidException("Token invalid or expired.");
        }
        user.assertCanRequestPasswordChange();
        revocationService.revokeAllForUser(user.getId(), now);
        user.changePassword(passwordHasher.hash(newPassword), now);
        request.resolve(now);
        userRepository.save(user);
        requestRepository.save(request);
    }
}
