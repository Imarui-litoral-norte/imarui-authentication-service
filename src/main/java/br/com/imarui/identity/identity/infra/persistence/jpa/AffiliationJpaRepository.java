package br.com.imarui.identity.identity.infra.persistence.jpa;

import br.com.imarui.identity.identity.core.domain.enums.affiliation.AffiliationStatus;
import br.com.imarui.identity.identity.infra.persistence.entity.affiliation.AffiliationEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AffiliationJpaRepository
        extends JpaRepository<AffiliationEntity, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            select affiliation
              from AffiliationJpaEntity affiliation
             where affiliation.id = :affiliationId
            """)
    Optional<AffiliationEntity> findByIdForUpdate(
            @Param("affiliationId") UUID affiliationId
    );

    List<AffiliationEntity> findByTenantId(UUID tenantId);

    List<AffiliationEntity> findByIdentityId(UUID identityId);

    boolean existsByTenantIdAndIdentityIdAndStatus(
            UUID tenantId,
            UUID identityId,
            AffiliationStatus status
    );

    @Query("""
            select (count(employee) > 0)
              from EmployeeAffiliationJpaEntity employee
             where employee.tenantId = :tenantId
               and employee.registration = :registration
            """)
    boolean existsEmployeeRegistration(
            @Param("tenantId") UUID tenantId,
            @Param("registration") String registration
    );

    @Query("""
            select (count(customer) > 0)
              from CustomerAffiliationJpaEntity customer
             where customer.tenantId = :tenantId
               and customer.customerCode = :customerCode
            """)
    boolean existsCustomerCode(
            @Param("tenantId") UUID tenantId,
            @Param("customerCode") String customerCode
    );

    @Query("""
            select (count(supplier) > 0)
              from SupplierAffiliationJpaEntity supplier
             where supplier.tenantId = :tenantId
               and supplier.supplierCode = :supplierCode
            """)
    boolean existsSupplierCode(
            @Param("tenantId") UUID tenantId,
            @Param("supplierCode") String supplierCode
    );
}
