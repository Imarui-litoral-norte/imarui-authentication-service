package br.com.imarui.identity.identity.core.domain.exceptions.user;

import br.com.imarui.identity.identity.core.domain.enums.user.AffiliationStatus;

import java.time.Instant;

public class InvalidAffiliationStateException extends RuntimeException {

    public InvalidAffiliationStateException(
            AffiliationStatus status,
            Instant endedAt
    ) {
        super(
                "Invalid affiliation state. "
                        + "status=" + status
                        + ", endedAt=" + endedAt
        );
    }
}