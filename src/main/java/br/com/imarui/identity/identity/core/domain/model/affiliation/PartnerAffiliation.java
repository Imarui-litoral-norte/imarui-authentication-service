package br.com.imarui.identity.identity.core.domain.model.affiliation;

import br.com.imarui.identity.identity.core.domain.enums.affiliation.AffiliationStatus;
import br.com.imarui.identity.identity.core.domain.enums.affiliation.AffiliationType;
import br.com.imarui.identity.identity.core.domain.model.affiliation.code.AffiliationId;
import br.com.imarui.identity.identity.core.domain.model.identity.IdentityId;
import br.com.imarui.identity.identity.core.domain.model.tenant.TenantId;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.util.Objects;

public final class PartnerAffiliation extends Affiliation {

    private PartnerAffiliation(
            AffiliationId id,
            TenantId tenantId,
            IdentityId identityId,
            AffiliationStatus status,
            Instant startedAt,
            Instant updatedAt,
            Instant endedAt
    ) {
        super(id, tenantId, identityId, status, startedAt, updatedAt, endedAt);
    }

    public static PartnerAffiliation create(
            @NotNull AffiliationId id,
            @NotNull TenantId tenantId,
            @NotNull IdentityId identityId,
            @NotNull Instant now
    ) {
        Objects.requireNonNull(now, "now cannot be null");

        return new PartnerAffiliation(
                id,
                tenantId,
                identityId,
                AffiliationStatus.ACTIVE,
                now,
                now,
                null
        );
    }

    public static PartnerAffiliation reconstitute(
            @NotNull AffiliationId id,
            @NotNull TenantId tenantId,
            @NotNull IdentityId identityId,
            @NotNull AffiliationStatus status,
            @NotNull Instant startedAt,
            @NotNull Instant updatedAt,
            @Nullable Instant endedAt
    ) {
        return new PartnerAffiliation(
                id,
                tenantId,
                identityId,
                status,
                startedAt,
                updatedAt,
                endedAt
        );
    }

    @Override
    public @NotNull AffiliationType getType() {
        return AffiliationType.PARTNER;
    }
}
