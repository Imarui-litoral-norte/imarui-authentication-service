package br.com.imarui.identity.identity.core.application.service.user;

import br.com.imarui.identity.identity.core.application.exceptions.user.RegistrationConflictException;
import br.com.imarui.identity.identity.core.application.exceptions.user.UserIdNotFoundException;
import br.com.imarui.identity.authentication.core.application.result.AuthTokens;
import br.com.imarui.identity.authentication.core.application.service.internal.TokenIssuanceService;
import br.com.imarui.identity.identity.core.domain.model.identity.Identity;
import br.com.imarui.identity.authentication.core.port.PasswordHasher;
import br.com.imarui.identity.identity.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;
    private final TokenIssuanceService tokenIssuanceService;
    private final Clock clock;

    public Identity register(
            String name,
            LocalDate birthDate,
            String email,
            String cpf,
            String rg,
            String passwordRaw,
            String phoneNumber
    ) {
        return registerUser(
                name,
                birthDate,
                email,
                cpf,
                rg,
                passwordRaw,
                phoneNumber
        );
    }

    @Transactional
    public AuthTokens registerAndAuthenticate(
            String name,
            LocalDate birthDate,
            String email,
            String cpf,
            String rg,
            String rawPassword,
            String phoneNumber
    ) {
        Identity user = registerUser(
                name,
                birthDate,
                email,
                cpf,
                rg,
                rawPassword,
                phoneNumber
        );

        Identity lockedUser = userRepository.findByIdForUpdate(user.getId())
                .orElseThrow(() -> new UserIdNotFoundException("Registered user not found."));
        Instant now = Instant.now(clock);
        AuthTokens tokens = tokenIssuanceService.issue(lockedUser, now);
        lockedUser.recordSuccessfulLogin(now);
        userRepository.save(lockedUser);
        return tokens;
    }

    private Identity registerUser(
            String name,
            LocalDate birthDate,
            String email,
            String cpf,
            String rg,
            String passwordRaw,
            String phoneNumber
    ) {
        Instant now = Instant.now(clock);

        if (userRepository.existsByCpf(cpf)
                || userRepository.existsByEmail(email)
                || userRepository.existsByRg(rg)) {
            throw new RegistrationConflictException();
        }

        String passwordHash = passwordHasher.hash(passwordRaw);

        Identity newUser = Identity.create(
                name,
                birthDate,
                email,
                cpf,
                rg,
                passwordHash,
                phoneNumber,
                now
        );

        return userRepository.create(newUser);
    }
}
