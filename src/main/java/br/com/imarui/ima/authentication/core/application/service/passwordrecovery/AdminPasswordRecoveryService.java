package br.com.imarui.ima.authentication.core.application.service.passwordrecovery;

import br.com.imarui.ima.authentication.core.application.exceptions.login.PasswordRecoveryRequestNotFoundException;
import br.com.imarui.ima.identity.core.application.exception.identity.UserIdNotFoundException;
import br.com.imarui.ima.authentication.core.application.result.IssuedPasswordResetToken;
import br.com.imarui.ima.authentication.core.application.result.IssuedTemporaryPassword;
import br.com.imarui.ima.authentication.core.application.result.PasswordResetLinkAdminResult;
import br.com.imarui.ima.authentication.core.application.result.TemporaryPasswordAdminResult;
import br.com.imarui.ima.authentication.core.application.result.admin.recovery.AdminPasswordRecoveryRequestResult;
import br.com.imarui.ima.authentication.core.application.service.internal.CredentialRevocationService;
import br.com.imarui.ima.authentication.core.application.service.internal.PasswordRecoveryRequestManager;
import br.com.imarui.ima.authentication.core.application.service.internal.PasswordResetTokenIssuer;
import br.com.imarui.ima.authentication.core.application.service.internal.TemporaryPasswordIssuer;
import br.com.imarui.ima.authentication.core.domain.model.PasswordRecoveryRequest;
import br.com.imarui.ima.identity.core.domain.model.identity.Identity;
import br.com.imarui.ima.authentication.core.repository.PasswordRecoveryRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminPasswordRecoveryService {

    private final PasswordRecoveryRequestRepository requestRepository;
    private final PasswordRecoveryRequestManager requestManager;
    private final TemporaryPasswordIssuer passwordIssuer;
    private final CredentialRevocationService revocationService;
    private final PasswordResetTokenIssuer tokenIssuer;
    private final Clock clock;

    @Transactional(readOnly = true)
    public List<AdminPasswordRecoveryRequestResult> findAll() {
        return AdminPasswordRecoveryRequestResult.from(requestRepository.findAll());
    }

    @Transactional(readOnly = true)
    public AdminPasswordRecoveryRequestResult findById(Long id) {
        return AdminPasswordRecoveryRequestResult.from(find(id));
    }

    @Transactional(readOnly = true)
    public List<AdminPasswordRecoveryRequestResult> findByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserIdNotFoundException(userId);
        }
        return AdminPasswordRecoveryRequestResult.from(requestRepository.findByUserId(userId));
    }

    @Transactional
    public AdminPasswordRecoveryRequestResult cancel(Long id) {
        PasswordRecoveryRequest request = requestRepository.findByIdForUpdate(id)
                .orElseThrow(() -> new PasswordRecoveryRequestNotFoundException(id));
        request.cancel(Instant.now(clock));
        return AdminPasswordRecoveryRequestResult.from(requestRepository.save(request));
    }

    @Transactional
    public TemporaryPasswordAdminResult generateTemporaryPassword(Long userId) {
        Instant now = Instant.now(clock);
        Identity user = userRepository.findByIdForUpdate(userId)
                .orElseThrow(() -> new UserIdNotFoundException("User target not found."));
        user.assertCanRequestPasswordChange();
        PasswordRecoveryRequest request = requestManager.getOrCreate(user.getId(), now);
        IssuedTemporaryPassword temporaryPassword = passwordIssuer.issue();
        revocationService.revokeAllForUser(user.getId(), now);
        request.changeToAdminTemporaryPasswordMethod(now);
        user.changeToTemporaryPassword(temporaryPassword.passwordHash(), now);
        userRepository.save(user);
        return new TemporaryPasswordAdminResult(
                temporaryPassword.rawPassword(), requestRepository.save(request)
        );
    }

    @Transactional
    public PasswordResetLinkAdminResult generateResetLink(Long userId) {
        Instant now = Instant.now(clock);
        Identity user = userRepository.findByIdForUpdate(userId)
                .orElseThrow(() -> new UserIdNotFoundException("User target not found."));
        user.assertCanRequestPasswordChange();
        PasswordRecoveryRequest request = requestManager.getOrCreate(user.getId(), now);
        IssuedPasswordResetToken token = tokenIssuer.issue();
        request.changeToAdminResetLinkMethod(token.tokenHash(), now);
        PasswordRecoveryRequest saved = requestRepository.save(request);
        return new PasswordResetLinkAdminResult(
                token.resetLink(),
                saved
        );
    }

    private PasswordRecoveryRequest find(Long id) {
        return requestRepository.findById(id)
                .orElseThrow(() -> new PasswordRecoveryRequestNotFoundException(id));
    }
}
