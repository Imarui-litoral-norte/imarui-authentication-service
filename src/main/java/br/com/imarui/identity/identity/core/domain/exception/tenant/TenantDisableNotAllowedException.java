package br.com.imarui.identity.identity.core.domain.exception.tenant;

import br.com.imarui.identity.identity.core.domain.enums.tenant.TenantStatus;
import br.com.imarui.identity.identity.core.domain.model.tenant.TenantId;

public final class TenantDisableNotAllowedException
        extends RuntimeException {

    public TenantDisableNotAllowedException(
            TenantId tenantId,
            TenantStatus currentStatus
    ) {
        super(
                "Tenant with id "
                        + tenantId.value()
                        + " cannot be disabled while status is "
                        + currentStatus
                        + "."
        );
    }
}