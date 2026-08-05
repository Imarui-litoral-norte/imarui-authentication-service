package br.com.imarui.ima.identity.core.application.result.identityapplication;

import br.com.imarui.ima.identity.core.domain.enums.identityapplication.IdentityApplicationStatus;
import br.com.imarui.ima.identity.core.domain.model.identityapplication.IdentityApplication;

import java.time.Instant;
import java.util.UUID;

public record IdentityApplicationResult(
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

    public static IdentityApplicationResult from(
            IdentityApplication application
    ) {
        return new IdentityApplicationResult(
                application.getId().value(),
                application.getFullName().value(),
                application.getCpf().value(),
                application.getEmail().value(),
                application.getPhoneNumber().value(),
                application.getStatus(),
                application.getRequestedAt(),
                application.getReviewedAt(),
                application.getReviewedBy() == null
                        ? null
                        : application.getReviewedBy().value(),
                application.getRejectionReason(),
                application.getResolvedIdentityId() == null
                        ? null
                        : application.getResolvedIdentityId().value(),
                application.getAssignedTenantId() == null
                        ? null
                        : application.getAssignedTenantId().value(),
                application.getResultingAffiliationId() == null
                        ? null
                        : application.getResultingAffiliationId().value()
        );
    }
}
