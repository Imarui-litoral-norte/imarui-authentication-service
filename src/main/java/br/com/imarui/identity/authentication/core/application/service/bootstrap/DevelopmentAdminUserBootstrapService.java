package br.com.imarui.identity.authentication.core.application.service.bootstrap;

import br.com.imarui.identity.identity.core.application.exception.identity.RegistrationConflictException;
import br.com.imarui.identity.identity.core.domain.model.identity.Identity;
import br.com.imarui.identity.authentication.core.port.PasswordHasher;
import br.com.imarui.identity.shared.bootstrap.AdminBootstrapConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class DevelopmentAdminUserBootstrapService {

    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;
    private final AdminBootstrapConfig adminBootstrapConfig;
    private final Clock clock;

    public Long createAdminUserIfMissing() {
        return userRepository
                .findByCpf(adminBootstrapConfig.cpf())
                .map(Identity::getId)
                .orElseGet(
                        this::createAdminUserHandlingConcurrency
                );
    }

    private Long createAdminUserHandlingConcurrency() {
        try {
            return createAdminUser();
        } catch (RegistrationConflictException exception) {
            return userRepository
                    .findByCpf(adminBootstrapConfig.cpf())
                    .map(Identity::getId)
                    .orElseThrow(() -> exception);
        }
    }

    private Long createAdminUser() {
        Instant now = Instant.now(clock);

        String passwordHash =
                passwordHasher.hash(
                        adminBootstrapConfig.password()
                );

        Identity user = Identity.create(
                adminBootstrapConfig.name(),
                adminBootstrapConfig.birthDate(),
                adminBootstrapConfig.email(),
                adminBootstrapConfig.cpf(),
                adminBootstrapConfig.rg(),
                passwordHash,
                adminBootstrapConfig.phoneNumber(),
                now
        );

        Identity savedUser =
                userRepository.create(user);

        return savedUser.getId();
    }
}
