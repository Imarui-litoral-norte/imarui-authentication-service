package br.com.imarui.identity.identity.core.application.service.user;

import br.com.imarui.identity.identity.core.application.exceptions.identity.UserIdNotFoundException;
import br.com.imarui.identity.authentication.core.application.result.admin.user.AdminUserResult;
import br.com.imarui.identity.authentication.core.application.service.internal.CredentialRevocationService;
import br.com.imarui.identity.identity.core.domain.model.identity.Identity;
import br.com.imarui.identity.identity.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUserService {

    private final UserRepository userRepository;
    private final CredentialRevocationService credentialRevocationService;
    private final Clock clock;

    @Transactional(readOnly = true)
    public List<AdminUserResult> findAll() {
        return AdminUserResult.from(
                userRepository.findAll()
        );
    }

    @Transactional(readOnly = true)
    public AdminUserResult findById(Long userId) {
        Identity user = findUserById(userId);

        return AdminUserResult.from(user);
    }

    @Transactional
    public AdminUserResult disable(Long userId) {
        Identity user =
                findUserByIdForUpdate(userId);

        Instant now = Instant.now(clock);

        user.disable(now);
        credentialRevocationService.revokeAllForUser(user.getId(), now);

        Identity savedUser =
                userRepository.save(user);

        return AdminUserResult.from(savedUser);
    }

    @Transactional
    public AdminUserResult enable(Long userId) {
        Identity user =
                findUserByIdForUpdate(userId);

        Instant now = Instant.now(clock);

        user.reactivate(now);

        Identity savedUser =
                userRepository.save(user);

        return AdminUserResult.from(savedUser);
    }

    private Identity findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(
                        () -> new UserIdNotFoundException(
                                userId
                        )
                );
    }

    private Identity findUserByIdForUpdate(
            Long userId
    ) {
        return userRepository
                .findByIdForUpdate(userId)
                .orElseThrow(
                        () -> new UserIdNotFoundException(
                                userId
                        )
                );
    }
}
