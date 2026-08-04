package br.com.imarui.ima.identity.api.dto.tenant.response;

import br.com.imarui.ima.identity.core.application.result.tenant.TenantResult;
import br.com.imarui.ima.identity.core.domain.enums.tenant.TenantStatus;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.UUID;

public record TenantResponse(
        UUID id,
        String code,
        String name,
        TenantStatus status,
        Instant createdAt,
        Instant updatedAt,
        Instant activatedAt,
        Instant disabledAt
) {

    public static TenantResponse from(
            @NotNull TenantResult result
    ) {
        return new TenantResponse(
                result.id(),
                result.code(),
                result.name(),
                result.status(),
                result.createdAt(),
                result.updatedAt(),
                result.activatedAt(),
                result.disabledAt()
        );
    }
}
