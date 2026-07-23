package br.com.imarui.authentication.core.application.service.bootstrap;

import br.com.imarui.authentication.core.application.exceptions.user.RegistrationConflictException;
import br.com.imarui.authentication.core.domain.model.User;
import br.com.imarui.authentication.core.port.PasswordHasher;
import br.com.imarui.authentication.core.repository.UserRepository;
import br.com.imarui.shared.bootstrap.AdminBootstrapConfig;
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
                .map(User::getId)
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
                    .map(User::getId)
                    .orElseThrow(() -> exception);
        }
    }

    private Long createAdminUser() {
        Instant now = Instant.now(clock);

        String passwordHash =
                passwordHasher.hash(
                        adminBootstrapConfig.password()
                );

        User user = User.create(
                adminBootstrapConfig.name(),
                adminBootstrapConfig.birthDate(),
                adminBootstrapConfig.email(),
                adminBootstrapConfig.cpf(),
                adminBootstrapConfig.rg(),
                passwordHash,
                adminBootstrapConfig.phoneNumber(),
                now
        );

        User savedUser =
                userRepository.create(user);

        return savedUser.getId();
    }
}
