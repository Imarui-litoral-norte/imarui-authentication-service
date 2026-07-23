package br.com.imarui.authentication.core.application.result;

import br.com.imarui.authentication.core.domain.enums.PasswordRecoveryRequestMethod;
import br.com.imarui.authentication.core.domain.enums.PasswordRecoveryRequestStatus;
import br.com.imarui.authentication.core.domain.model.PasswordRecoveryRequest;

import java.time.Instant;

public record PublicPasswordRecoveryResult(
        PasswordRecoveryRequestStatus status,
        PasswordRecoveryRequestMethod method,
        Instant createdAt,
        Instant expiresAt,
        Instant resolvedAt,
        Instant cancelledAt
) {

    public static PublicPasswordRecoveryResult from(
            PasswordRecoveryRequest request
    ) {
        return new PublicPasswordRecoveryResult(
                request.getStatus(),
                request.getMethod(),
                request.getCreatedAt(),
                request.getExpiresAt(),
                request.getResolvedAt(),
                request.getCancelledAt()
        );
    }

    public static PublicPasswordRecoveryResult pending(
            Instant now,
            Instant expiresAt
    ) {
        return pending(now, expiresAt, PasswordRecoveryRequestMethod.UNDEFINED);
    }

    public static PublicPasswordRecoveryResult pending(
            Instant now,
            Instant expiresAt,
            PasswordRecoveryRequestMethod method
    ) {
        return new PublicPasswordRecoveryResult(
                PasswordRecoveryRequestStatus.OPEN,
                method,
                now,
                expiresAt,
                null,
                null
        );
    }
}
