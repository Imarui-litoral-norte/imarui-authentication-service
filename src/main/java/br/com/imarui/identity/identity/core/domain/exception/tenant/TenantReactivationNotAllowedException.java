package br.com.imarui.identity.identity.core.domain.exception.tenant;

import br.com.imarui.identity.identity.core.domain.enums.tenant.TenantStatus;
import br.com.imarui.identity.identity.core.domain.model.tenant.TenantId;
import lombok.Getter;

@Getter
public final class TenantReactivationNotAllowedException
        extends RuntimeException {

    private final TenantId tenantId;
    private final TenantStatus currentStatus;

    public TenantReactivationNotAllowedException(
            TenantId tenantId,
            TenantStatus currentStatus
    ) {
        super(
                "Tenant with id "
                        + tenantId.value()
                        + " cannot be reactivated while status is "
                        + currentStatus
                        + "."
        );
        this.tenantId = tenantId;
        this.currentStatus = currentStatus;
    }
}
