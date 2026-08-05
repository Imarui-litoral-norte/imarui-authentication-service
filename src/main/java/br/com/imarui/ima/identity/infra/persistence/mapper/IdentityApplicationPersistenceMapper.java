package br.com.imarui.ima.identity.infra.persistence.mapper;

import br.com.imarui.ima.identity.core.domain.model.affiliation.AffiliationId;
import br.com.imarui.ima.identity.core.domain.model.identity.Email;
import br.com.imarui.ima.identity.core.domain.model.identity.IdentityFullName;
import br.com.imarui.ima.identity.core.domain.model.identity.IdentityId;
import br.com.imarui.ima.identity.core.domain.model.identity.PhoneNumber;
import br.com.imarui.ima.identity.core.domain.model.identity.person.Cpf;
import br.com.imarui.ima.identity.core.domain.model.identityapplication.IdentityApplication;
import br.com.imarui.ima.identity.core.domain.model.identityapplication.IdentityApplicationId;
import br.com.imarui.ima.identity.core.domain.model.tenant.TenantId;
import br.com.imarui.ima.identity.infra.persistence.entity.identityapplication.IdentityApplicationEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class IdentityApplicationPersistenceMapper {

    public IdentityApplicationEntity toEntity(
            IdentityApplication application
    ) {
        return new IdentityApplicationEntity(
                application.getId().value(),
                application.getFullName().value(),
                application.getCpf().value(),
                application.getEmail().value(),
                application.getPhoneNumber().value(),
                application.getStatus(),
                application.getRequestedAt(),
                application.getReviewedAt(),
                valueOf(application.getReviewedBy()),
                application.getRejectionReason(),
                valueOf(application.getResolvedIdentityId()),
                valueOf(application.getAssignedTenantId()),
                valueOf(application.getResultingAffiliationId())
        );
    }

    public void updateEntity(
            IdentityApplication application,
            IdentityApplicationEntity entity
    ) {
        entity.updateFrom(
                application.getStatus(),
                application.getReviewedAt(),
                valueOf(application.getReviewedBy()),
                application.getRejectionReason(),
                valueOf(application.getResolvedIdentityId()),
                valueOf(application.getAssignedTenantId()),
                valueOf(application.getResultingAffiliationId())
        );
    }

    public IdentityApplication toDomain(
            IdentityApplicationEntity entity
    ) {
        return IdentityApplication.reconstitute(
                IdentityApplicationId.from(entity.getId()),
                IdentityFullName.from(entity.getFullName()),
                Cpf.from(entity.getCpf()),
                Email.from(entity.getEmail()),
                PhoneNumber.from(entity.getPhoneNumber()),
                entity.getStatus(),
                entity.getRequestedAt(),
                entity.getReviewedAt(),
                identityIdFrom(entity.getReviewedByIdentityId()),
                entity.getRejectionReason(),
                identityIdFrom(entity.getResolvedIdentityId()),
                tenantIdFrom(entity.getAssignedTenantId()),
                affiliationIdFrom(entity.getResultingAffiliationId())
        );
    }

    private UUID valueOf(IdentityId identityId) {
        return identityId == null ? null : identityId.value();
    }

    private UUID valueOf(TenantId tenantId) {
        return tenantId == null ? null : tenantId.value();
    }

    private UUID valueOf(AffiliationId affiliationId) {
        return affiliationId == null ? null : affiliationId.value();
    }

    private IdentityId identityIdFrom(UUID value) {
        return value == null ? null : IdentityId.from(value);
    }

    private TenantId tenantIdFrom(UUID value) {
        return value == null ? null : TenantId.from(value);
    }

    private AffiliationId affiliationIdFrom(UUID value) {
        return value == null ? null : AffiliationId.from(value);
    }
}
