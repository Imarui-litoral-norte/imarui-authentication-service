package br.com.imarui.ima.identity.api.dto.identityapplication.response;

import br.com.imarui.ima.identity.core.application.result.identityapplication.IdentityApplicationResult;
import br.com.imarui.ima.identity.core.domain.enums.identityapplication.IdentityApplicationStatus;

import java.time.Instant;
import java.util.UUID;

public record IdentityApplicationResponse(
        UUID id,
        String fullName,
        String cpf,
        String email,
        String phoneNumber,
        IdentityApplicationStatus status,
        Instant requestedAt,
        Instant reviewedAt,
        UUID reviewedBy,
        String rejectionReason,
        UUID resolvedIdentityId,
        UUID assignedTenantId,
        UUID resultingAffiliationId
) {

    public static IdentityApplicationResponse from(
            IdentityApplicationResult result
    ) {
        return new IdentityApplicationResponse(
                result.id(),
                result.fullName(),
                result.cpf(),
                result.email(),
                result.phoneNumber(),
                result.status(),
                result.requestedAt(),
                result.reviewedAt(),
                result.reviewedBy(),
                result.rejectionReason(),
                result.resolvedIdentityId(),
                result.assignedTenantId(),
                result.resultingAffiliationId()
        );
    }
}
