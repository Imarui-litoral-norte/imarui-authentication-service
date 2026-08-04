package br.com.imarui.ima.identity.core.domain.exception.tenant;

import br.com.imarui.ima.identity.core.domain.enums.tenant.TenantStatus;
import br.com.imarui.ima.identity.core.domain.model.tenant.TenantId;
import lombok.Getter;

@Getter
public final class TenantRenameNotAllowedException
        extends RuntimeException {

    private final TenantId tenantId;
    private final TenantStatus currentStatus;

    public TenantRenameNotAllowedException(
            TenantId tenantId,
            TenantStatus currentStatus
    ) {
        super(
                "Tenant with id "
                        + tenantId.value()
                        + " cannot be renamed while status is "
                        + currentStatus
                        + "."
        );
        this.tenantId = tenantId;
        this.currentStatus = currentStatus;
    }
}
