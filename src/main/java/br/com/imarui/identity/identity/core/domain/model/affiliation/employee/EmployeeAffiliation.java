package br.com.imarui.identity.identity.core.domain.model.affiliation.employee;

import br.com.imarui.identity.identity.core.domain.enums.affiliation.AffiliationStatus;
import br.com.imarui.identity.identity.core.domain.enums.affiliation.AffiliationType;
import br.com.imarui.identity.identity.core.domain.model.affiliation.Affiliation;
import br.com.imarui.identity.identity.core.domain.model.affiliation.AffiliationId;
import br.com.imarui.identity.identity.core.domain.model.identity.IdentityId;
import br.com.imarui.identity.identity.core.domain.model.tenant.TenantId;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.util.Objects;

public final class EmployeeAffiliation extends Affiliation {

    private final EmployeeRegistration registration;

    private EmployeeAffiliation(
            AffiliationId id,
            TenantId tenantId,
            IdentityId identityId,
            EmployeeRegistration registration,
            AffiliationStatus status,
            Instant startedAt,
            Instant updatedAt,
            Instant endedAt
    ) {
        super(id, tenantId, identityId, status, startedAt, updatedAt, endedAt);
        this.registration = Objects.requireNonNull(
                registration,
                "registration cannot be null"
        );
    }

    public static EmployeeAffiliation create(
            @NotNull AffiliationId id,
            @NotNull TenantId tenantId,
            @NotNull IdentityId identityId,
            @NotNull EmployeeRegistration registration,
            @NotNull Instant now
    ) {
        Objects.requireNonNull(now, "now cannot be null");

        return new EmployeeAffiliation(
                id,
                tenantId,
                identityId,
                registration,
                AffiliationStatus.ACTIVE,
                now,
                now,
                null
        );
    }

    public static EmployeeAffiliation reconstitute(
            @NotNull AffiliationId id,
            @NotNull TenantId tenantId,
            @NotNull IdentityId identityId,
            @NotNull EmployeeRegistration registration,
            @NotNull AffiliationStatus status,
            @NotNull Instant startedAt,
            @NotNull Instant updatedAt,
            @Nullable Instant endedAt
    ) {
        return new EmployeeAffiliation(
                id,
                tenantId,
                identityId,
                registration,
                status,
                startedAt,
                updatedAt,
                endedAt
        );
    }

    @Override
    public @NotNull AffiliationType getType() {
        return AffiliationType.EMPLOYEE;
    }

    public @NotNull EmployeeRegistration getRegistration() {
        return registration;
    }
}
