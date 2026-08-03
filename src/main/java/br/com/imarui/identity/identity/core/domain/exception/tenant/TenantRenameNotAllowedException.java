package br.com.imarui.identity.identity.core.domain.exception.tenant;

import br.com.imarui.identity.identity.core.domain.enums.tenant.TenantStatus;
import br.com.imarui.identity.identity.core.domain.model.tenant.TenantId;

public final class TenantRenameNotAllowedException
        extends RuntimeException {

    public TenantRenameNotAllowedException(
            TenantId tenantId,
            TenantStatus status
    ) {
        super(
                "Tenant with id "
                        + tenantId.value()
                        + " cannot be renamed while status is "
                        + status
                        + "."
        );
    }
}