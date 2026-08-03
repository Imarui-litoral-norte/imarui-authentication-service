package br.com.imarui.identity.identity.infra.persistence.adapter;

import br.com.imarui.identity.identity.core.domain.model.tenant.*;
import br.com.imarui.identity.identity.core.repository.TenantRepository;
import br.com.imarui.identity.identity.infra.persistence.entity.tenant.TenantEntity;
import br.com.imarui.identity.identity.infra.persistence.jpa.TenantJpaRepository;
import br.com.imarui.identity.identity.infra.persistence.mapper.TenantPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TenantRepositoryAdapter implements TenantRepository {

    private final TenantJpaRepository jpaRepository;
    private final TenantPersistenceMapper mapper;

    public Tenant save(Tenant tenant) {
        TenantEntity entity = jpaRepository
                .findById(tenant.getId().value())
                .map(existing -> {
                    mapper.updateEntity(tenant, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toEntity(tenant));

        return mapper.toDomain(
                jpaRepository.save(entity)
        );
    }

    public Optional<Tenant> findById(TenantId tenantId) {
        return jpaRepository
                .findById(tenantId.value())
                .map(mapper::toDomain);
    }

    public Optional<Tenant> findByIdForUpdate(TenantId tenantId) {
        return jpaRepository
                .findByIdForUpdate(tenantId.value())
                .map(mapper::toDomain);
    }

    public Optional<Tenant> findByCode(TenantCode code) {
        return jpaRepository
                .findByCodeIgnoreCase(code.value())
                .map(mapper::toDomain);
    }

    public boolean existsByCode(TenantCode code) {
        return jpaRepository.existsByCodeIgnoreCase(
                code.value()
        );
    }
}
