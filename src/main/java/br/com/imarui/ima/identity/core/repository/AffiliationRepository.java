package br.com.imarui.ima.identity.core.repository;

import br.com.imarui.ima.identity.core.domain.model.affiliation.Affiliation;
import br.com.imarui.ima.identity.core.domain.model.affiliation.AffiliationId;
import br.com.imarui.ima.identity.core.domain.model.affiliation.customer.CustomerCode;
import br.com.imarui.ima.identity.core.domain.model.affiliation.employee.EmployeeRegistration;
import br.com.imarui.ima.identity.core.domain.model.affiliation.supplier.SupplierCode;
import br.com.imarui.ima.identity.core.domain.model.identity.IdentityId;
import br.com.imarui.ima.identity.core.domain.model.tenant.TenantId;

import java.util.List;
import java.util.Optional;

public interface AffiliationRepository {

    Affiliation save(Affiliation affiliation);

    Optional<Affiliation> findById(AffiliationId affiliationId);

    Optional<Affiliation> findByIdForUpdate(
            AffiliationId affiliationId
    );

    List<Affiliation> findByTenantId(TenantId tenantId);

    List<Affiliation> findByIdentityId(IdentityId identityId);

    boolean existsActiveByTenantAndIdentity(
            TenantId tenantId,
            IdentityId identityId
    );

    boolean existsEmployeeRegistration(
            TenantId tenantId,
            EmployeeRegistration registration
    );

    boolean existsCustomerCode(
            TenantId tenantId,
            CustomerCode customerCode
    );

    boolean existsSupplierCode(
            TenantId tenantId,
            SupplierCode supplierCode
    );
}
