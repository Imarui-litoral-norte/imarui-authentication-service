package br.com.imarui.ima.identity.infra.persistence.mapper;

import br.com.imarui.ima.identity.core.domain.model.affiliation.*;
import br.com.imarui.ima.identity.core.domain.model.affiliation.customer.*;
import br.com.imarui.ima.identity.core.domain.model.affiliation.employee.*;
import br.com.imarui.ima.identity.core.domain.model.affiliation.supplier.*;
import br.com.imarui.ima.identity.core.domain.model.identity.IdentityId;
import br.com.imarui.ima.identity.core.domain.model.tenant.TenantId;
import br.com.imarui.ima.identity.infra.persistence.entity.affiliation.*;
import org.springframework.stereotype.Component;

@Component
public class AffiliationPersistenceMapper {

    public AffiliationEntity toEntity(Affiliation affiliation) {
        if (affiliation instanceof EmployeeAffiliation employee) {
            return new EmployeeAffiliationEntity(
                    employee.getId().value(),
                    employee.getTenantId().value(),
                    employee.getIdentityId().value(),
                    employee.getRegistration().value(),
                    employee.getStatus(),
                    employee.getStartedAt(),
                    employee.getUpdatedAt(),
                    employee.getEndedAt()
            );
        }

        if (affiliation instanceof CustomerAffiliation customer) {
            return new CustomerAffiliationEntity(
                    customer.getId().value(),
                    customer.getTenantId().value(),
                    customer.getIdentityId().value(),
                    customer.getCustomerCode().value(),
                    customer.getStatus(),
                    customer.getStartedAt(),
                    customer.getUpdatedAt(),
                    customer.getEndedAt()
            );
        }

        if (affiliation instanceof SupplierAffiliation supplier) {
            return new SupplierAffiliationJpaEntity(
                    supplier.getId().value(),
                    supplier.getTenantId().value(),
                    supplier.getIdentityId().value(),
                    supplier.getSupplierCode().value(),
                    supplier.getStatus(),
                    supplier.getStartedAt(),
                    supplier.getUpdatedAt(),
                    supplier.getEndedAt()
            );
        }

        if (affiliation instanceof PartnerAffiliation partner) {
            return new PartnerAffiliationEntity(
                    partner.getId().value(),
                    partner.getTenantId().value(),
                    partner.getIdentityId().value(),
                    partner.getStatus(),
                    partner.getStartedAt(),
                    partner.getUpdatedAt(),
                    partner.getEndedAt()
            );
        }

        if (affiliation instanceof ExternalAffiliation external) {
            return new ExternalAffiliationEntity(
                    external.getId().value(),
                    external.getTenantId().value(),
                    external.getIdentityId().value(),
                    external.getStatus(),
                    external.getStartedAt(),
                    external.getUpdatedAt(),
                    external.getEndedAt()
            );
        }

        throw unsupported(affiliation.getClass());
    }

    public void updateEntity(
            Affiliation affiliation,
            AffiliationEntity entity
    ) {
        if (
                affiliation instanceof EmployeeAffiliation
                        && entity instanceof EmployeeAffiliationEntity target
        ) {
            target.synchronize(
                    affiliation.getStatus(),
                    affiliation.getUpdatedAt(),
                    affiliation.getEndedAt()
            );
            return;
        }

        if (
                affiliation instanceof CustomerAffiliation
                        && entity instanceof CustomerAffiliationEntity target
        ) {
            target.synchronize(
                    affiliation.getStatus(),
                    affiliation.getUpdatedAt(),
                    affiliation.getEndedAt()
            );
            return;
        }

        if (
                affiliation instanceof SupplierAffiliation
                        && entity instanceof SupplierAffiliationJpaEntity target
        ) {
            target.synchronize(
                    affiliation.getStatus(),
                    affiliation.getUpdatedAt(),
                    affiliation.getEndedAt()
            );
            return;
        }

        if (
                affiliation instanceof PartnerAffiliation
                        && entity instanceof PartnerAffiliationEntity target
        ) {
            target.synchronize(
                    affiliation.getStatus(),
                    affiliation.getUpdatedAt(),
                    affiliation.getEndedAt()
            );
            return;
        }

        if (
                affiliation instanceof ExternalAffiliation
                        && entity instanceof ExternalAffiliationEntity target
        ) {
            target.synchronize(
                    affiliation.getStatus(),
                    affiliation.getUpdatedAt(),
                    affiliation.getEndedAt()
            );
            return;
        }

        throw new IllegalArgumentException(
                "Affiliation domain and persistence types do not match."
        );
    }

    public Affiliation toDomain(AffiliationEntity entity) {
        if (entity instanceof EmployeeAffiliationEntity employee) {
            return EmployeeAffiliation.reconstitute(
                    AffiliationId.from(employee.getId()),
                    TenantId.from(employee.getTenantId()),
                    IdentityId.from(employee.getIdentityId()),
                    EmployeeRegistration.from(employee.getRegistration()),
                    employee.getStatus(),
                    employee.getStartedAt(),
                    employee.getUpdatedAt(),
                    employee.getEndedAt()
            );
        }

        if (entity instanceof CustomerAffiliationEntity customer) {
            return CustomerAffiliation.reconstitute(
                    AffiliationId.from(customer.getId()),
                    TenantId.from(customer.getTenantId()),
                    IdentityId.from(customer.getIdentityId()),
                    CustomerCode.from(customer.getCustomerCode()),
                    customer.getStatus(),
                    customer.getStartedAt(),
                    customer.getUpdatedAt(),
                    customer.getEndedAt()
            );
        }

        if (entity instanceof SupplierAffiliationJpaEntity supplier) {
            return SupplierAffiliation.reconstitute(
                    AffiliationId.from(supplier.getId()),
                    TenantId.from(supplier.getTenantId()),
                    IdentityId.from(supplier.getIdentityId()),
                    SupplierCode.from(supplier.getSupplierCode()),
                    supplier.getStatus(),
                    supplier.getStartedAt(),
                    supplier.getUpdatedAt(),
                    supplier.getEndedAt()
            );
        }

        if (entity instanceof PartnerAffiliationEntity partner) {
            return PartnerAffiliation.reconstitute(
                    AffiliationId.from(partner.getId()),
                    TenantId.from(partner.getTenantId()),
                    IdentityId.from(partner.getIdentityId()),
                    partner.getStatus(),
                    partner.getStartedAt(),
                    partner.getUpdatedAt(),
                    partner.getEndedAt()
            );
        }

        if (entity instanceof ExternalAffiliationEntity external) {
            return ExternalAffiliation.reconstitute(
                    AffiliationId.from(external.getId()),
                    TenantId.from(external.getTenantId()),
                    IdentityId.from(external.getIdentityId()),
                    external.getStatus(),
                    external.getStartedAt(),
                    external.getUpdatedAt(),
                    external.getEndedAt()
            );
        }

        throw unsupported(entity.getClass());
    }

    private IllegalArgumentException unsupported(Class<?> type) {
        return new IllegalArgumentException(
                "Unsupported affiliation persistence type: " + type.getName()
        );
    }
}
