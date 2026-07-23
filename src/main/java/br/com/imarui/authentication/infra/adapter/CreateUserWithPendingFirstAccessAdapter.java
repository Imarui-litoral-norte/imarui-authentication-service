package br.com.imarui.authentication.infra.adapter;

import br.com.imarui.authentication.core.domain.model.User;
import br.com.imarui.authentication.core.port.PasswordHasher;
import br.com.imarui.authentication.core.repository.UserRepository;
import br.com.imarui.memberships.core.port.CreateUserWithPendingFirstAccessPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Instant;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreateUserWithPendingFirstAccessAdapter
        implements CreateUserWithPendingFirstAccessPort {

    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;
    private final Clock clock;

    @Override
    public Long createPendingUser(
            String name,
            String email,
            String cpf,
            String phoneNumber
    ) {
        Instant now = Instant.now(clock);

        String temporaryPassword =
                UUID.randomUUID().toString();

        String passwordHash =
                passwordHasher.hash(temporaryPassword);

        User newUser = User.createPendingFirstAccess(
                name,
                email,
                cpf,
                passwordHash,
                phoneNumber,
                now
        );

        User savedUser =
                userRepository.create(newUser);

        return savedUser.getId();
    }
}
