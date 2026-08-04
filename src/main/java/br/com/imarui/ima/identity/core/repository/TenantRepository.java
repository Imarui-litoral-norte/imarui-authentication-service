package br.com.imarui.ima.identity.core.repository;

import br.com.imarui.ima.identity.core.domain.model.tenant.Tenant;
import br.com.imarui.ima.identity.core.domain.model.tenant.TenantCode;
import br.com.imarui.ima.identity.core.domain.model.tenant.TenantId;

import java.util.Optional;

public interface TenantRepository {

    Tenant save(Tenant tenant);

    Optional<Tenant> findById(TenantId tenantId);

    Optional<Tenant> findByIdForUpdate(TenantId tenantId);

    Optional<Tenant> findByCode(TenantCode code);

    boolean existsByCode(TenantCode code);
}
