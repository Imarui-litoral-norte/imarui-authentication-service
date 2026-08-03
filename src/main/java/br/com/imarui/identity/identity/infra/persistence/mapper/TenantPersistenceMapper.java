package br.com.imarui.identity.identity.infra.persistence.mapper;

import br.com.imarui.identity.identity.core.domain.model.tenant.*;
import br.com.imarui.identity.identity.infra.persistence.entity.tenant.TenantEntity;
import org.springframework.stereotype.Component;

@Component
public class TenantPersistenceMapper {

    public TenantEntity toEntity(Tenant tenant) {
        return new TenantEntity(
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

    public void updateEntity(
            Tenant tenant,
            TenantEntity entity
    ) {
        entity.synchronize(
                tenant.getName().value(),
                tenant.getStatus(),
                tenant.getUpdatedAt(),
                tenant.getActivatedAt(),
                tenant.getDisabledAt()
        );
    }

    public Tenant toDomain(TenantEntity entity) {
        return Tenant.reconstitute(
                TenantId.from(entity.getId()),
                TenantCode.from(entity.getCode()),
                TenantName.from(entity.getName()),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getActivatedAt(),
                entity.getDisabledAt()
        );
    }
}
