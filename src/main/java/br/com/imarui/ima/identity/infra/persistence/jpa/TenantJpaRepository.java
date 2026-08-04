package br.com.imarui.ima.identity.infra.persistence.jpa;

import br.com.imarui.ima.identity.infra.persistence.entity.tenant.TenantEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface TenantJpaRepository
        extends JpaRepository<TenantEntity, UUID> {

    boolean existsByCodeIgnoreCase(String code);

    Optional<TenantEntity> findByCodeIgnoreCase(String code);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            select tenant
              from TenantJpaEntity tenant
             where tenant.id = :tenantId
            """)
    Optional<TenantEntity> findByIdForUpdate(
            @Param("tenantId") UUID tenantId
    );
}
