package br.com.imarui.identity.authentication.core.application.service.internal;

import br.com.imarui.identity.authentication.core.application.result.IssuedTemporaryPassword;
import br.com.imarui.identity.authentication.core.port.PasswordHasher;
import br.com.imarui.identity.authentication.core.port.RandomPasswordGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TemporaryPasswordIssuer {
    private final RandomPasswordGenerator passwordGenerator;
    private final PasswordHasher passwordHasher;

    public IssuedTemporaryPassword issue() {
        String rawPassword = passwordGenerator.generateSecurePassword();
        return new IssuedTemporaryPassword(rawPassword, passwordHasher.hash(rawPassword));
    }
}
