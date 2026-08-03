package br.com.imarui.identity.identity.core.application.exception.tenant;

import br.com.imarui.identity.identity.core.domain.model.tenant.TenantId;

public class TenantNotFoundException extends RuntimeException {

    public TenantNotFoundException(TenantId tenantId) {
        super(
                "Tenant not found with id: "
                        + tenantId.value()
        );
    }
}