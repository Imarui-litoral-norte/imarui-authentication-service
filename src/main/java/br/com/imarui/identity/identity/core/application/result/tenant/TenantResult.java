package br.com.imarui.identity.identity.core.application.result.tenant;

import br.com.imarui.identity.identity.core.domain.enums.tenant.TenantStatus;
import br.com.imarui.identity.identity.core.domain.model.tenant.Tenant;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.util.UUID;

public record TenantResult(
        @NotNull UUID id,
        @NotNull String code,
        @NotNull String name,
        @NotNull TenantStatus status,
        @NotNull Instant createdAt,
        @NotNull Instant updatedAt,
        @Nullable Instant activatedAt,
        @Nullable Instant disabledAt
) {

    public static TenantResult from(Tenant tenant) {
        return new TenantResult(
                tenant.getId().value(),
                tenant.getCode().value(),
                tenant.getName().value(),
                tenant.getStatus(),
                tenant.getCreatedAt(),
                tenant.getUpdatedAt(),
                tenant.getActivatedAt(),
                tenant.getDisabledAt()
        );
    }
}