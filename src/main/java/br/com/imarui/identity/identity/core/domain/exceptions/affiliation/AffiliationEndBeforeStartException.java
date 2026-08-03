package br.com.imarui.identity.identity.core.domain.exceptions.affiliation;

import java.time.Instant;

public class AffiliationEndBeforeStartException extends RuntimeException {

    public AffiliationEndBeforeStartException(
            Instant startedAt,
            Instant endedAt
    ) {
        super(
                "Affiliation end instant cannot be before its start instant. "
                        + "startedAt=" + startedAt
                        + ", endedAt=" + endedAt
        );
    }
}