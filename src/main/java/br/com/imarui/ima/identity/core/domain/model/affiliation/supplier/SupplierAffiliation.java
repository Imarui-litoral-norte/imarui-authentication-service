package br.com.imarui.ima.identity.core.domain.model.affiliation.supplier;

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

public final class SupplierAffiliation extends Affiliation {

    private final SupplierCode supplierCode;

    private SupplierAffiliation(
            AffiliationId id,
            TenantId tenantId,
            IdentityId identityId,
            SupplierCode supplierCode,
            AffiliationStatus status,
            Instant startedAt,
            Instant updatedAt,
            Instant endedAt
    ) {
        super(id, tenantId, identityId, status, startedAt, updatedAt, endedAt);
        this.supplierCode = Objects.requireNonNull(
                supplierCode,
                "supplierCode cannot be null"
        );
    }

    public static SupplierAffiliation create(
            @NotNull AffiliationId id,
            @NotNull TenantId tenantId,
            @NotNull IdentityId identityId,
            @NotNull SupplierCode supplierCode,
            @NotNull Instant now
    ) {
        Objects.requireNonNull(now, "now cannot be null");

        return new SupplierAffiliation(
                id,
                tenantId,
                identityId,
                supplierCode,
                AffiliationStatus.ACTIVE,
                now,
                now,
                null
        );
    }

    public static SupplierAffiliation reconstitute(
            @NotNull AffiliationId id,
            @NotNull TenantId tenantId,
            @NotNull IdentityId identityId,
            @NotNull SupplierCode supplierCode,
            @NotNull AffiliationStatus status,
            @NotNull Instant startedAt,
            @NotNull Instant updatedAt,
            @Nullable Instant endedAt
    ) {
        return new SupplierAffiliation(
                id,
                tenantId,
                identityId,
                supplierCode,
                status,
                startedAt,
                updatedAt,
                endedAt
        );
    }

    @Override
    public @NotNull AffiliationType getType() {
        return AffiliationType.SUPPLIER;
    }

    public @NotNull SupplierCode getSupplierCode() {
        return supplierCode;
    }
}
