package br.com.imarui.identity.identity.core.domain.model;

import br.com.imarui.identity.identity.core.domain.enums.user.AffiliationType;
import br.com.imarui.identity.identity.core.domain.model.id.AffiliationId;
import br.com.imarui.identity.identity.core.domain.model.id.OrganizationId;

import java.time.Instant;

public class Affiliation {
    private final AffiliationId id;
    private final OrganizationId organizationId;
    private final AffiliationType type;

    private AffiliationStatus status;

    private final Instant startedAt;
    private Instant endedAt;
}
