package br.com.imarui.ima.identity.core.domain.model.affiliation.customer;

import br.com.imarui.ima.identity.core.domain.enums.affiliation.AffiliationStatus;
import br.com.imarui.ima.identity.core.domain.enums.affiliation.AffiliationType;
import br.com.imarui.ima.identity.core.domain.model.affiliation.Affiliation;
import br.com.imarui.ima.identity.core.domain.model.affiliation.AffiliationId;
import br.com.imarui.ima.identity.core.domain.model.identity.IdentityId;
import br.com.imarui.ima.identity.core.domain.model.tenant.TenantId;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.util.Objects;

public final class CustomerAffiliation extends Affiliation {

    private final CustomerCode customerCode;

    private CustomerAffiliation(
            AffiliationId id,
            TenantId tenantId,
            IdentityId identityId,
            CustomerCode customerCode,
            AffiliationStatus status,
            Instant startedAt,
            Instant updatedAt,
            Instant endedAt
    ) {
        super(id, tenantId, identityId, status, startedAt, updatedAt, endedAt);
        this.customerCode = Objects.requireNonNull(
                customerCode,
                "customerCode cannot be null"
        );
    }

    public static CustomerAffiliation create(
            @NotNull AffiliationId id,
            @NotNull TenantId tenantId,
            @NotNull IdentityId identityId,
            @NotNull CustomerCode customerCode,
            @NotNull Instant now
    ) {
        Objects.requireNonNull(now, "now cannot be null");

        return new CustomerAffiliation(
                id,
                tenantId,
                identityId,
                customerCode,
                AffiliationStatus.ACTIVE,
                now,
                now,
                null
        );
    }

    public static CustomerAffiliation reconstitute(
            @NotNull AffiliationId id,
            @NotNull TenantId tenantId,
            @NotNull IdentityId identityId,
            @NotNull CustomerCode customerCode,
            @NotNull AffiliationStatus status,
            @NotNull Instant startedAt,
            @NotNull Instant updatedAt,
            @Nullable Instant endedAt
    ) {
        return new CustomerAffiliation(
                id,
                tenantId,
                identityId,
                customerCode,
                status,
                startedAt,
                updatedAt,
                endedAt
        );
    }

    @Override
    public @NotNull AffiliationType getType() {
        return AffiliationType.CUSTOMER;
    }

    public @NotNull CustomerCode getCustomerCode() {
        return customerCode;
    }
}
