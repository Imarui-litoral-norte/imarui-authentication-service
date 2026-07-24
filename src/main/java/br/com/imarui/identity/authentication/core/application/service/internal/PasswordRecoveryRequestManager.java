package br.com.imarui.identity.authentication.core.application.service.internal;

import br.com.imarui.identity.authentication.core.application.result.PublicPasswordRecoveryResult;
import br.com.imarui.identity.authentication.core.domain.model.PasswordRecoveryRequest;
import br.com.imarui.identity.authentication.core.domain.enums.PasswordRecoveryRequestMethod;
import br.com.imarui.identity.authentication.core.port.ApplicationTimeProperties;
import br.com.imarui.identity.authentication.core.repository.PasswordRecoveryRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class PasswordRecoveryRequestManager {
    private final PasswordRecoveryRequestRepository repository;
    private final ApplicationTimeProperties timeProperties;

    public PasswordRecoveryRequest getOrCreate(Long userId, Instant now) {
        return repository.findOpenByUserIdForUpdate(userId, now)
                .orElseGet(() -> repository.save(PasswordRecoveryRequest.createOpenRequest(
                        userId, now, now.plus(timeProperties.passwordRecoveryRequestTtl())
                )));
    }

    public PublicPasswordRecoveryResult genericResult(Instant now) {
        return PublicPasswordRecoveryResult.pending(
                now, now.plus(timeProperties.passwordRecoveryRequestTtl())
        );
    }

    public PublicPasswordRecoveryResult genericEmailResult(Instant now) {
        return PublicPasswordRecoveryResult.pending(
                now,
                now.plus(timeProperties.passwordRecoveryRequestTtl()),
                PasswordRecoveryRequestMethod.EMAIL_TOKEN
        );
    }
}
