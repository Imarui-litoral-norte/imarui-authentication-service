package br.com.imarui.ima.identity.core.application.exception.tenant;

import br.com.imarui.ima.identity.core.domain.model.tenant.TenantId;
import lombok.Getter;

@Getter
public final class TenantNotFoundException extends RuntimeException {

    private final TenantId tenantId;

    public TenantNotFoundException(TenantId tenantId) {
        super(
                "Tenant not found with id: "
                        + tenantId.value()
        );
        this.tenantId = tenantId;
    }
}
