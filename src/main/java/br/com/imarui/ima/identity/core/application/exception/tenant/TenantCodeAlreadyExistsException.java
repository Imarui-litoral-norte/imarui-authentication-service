package br.com.imarui.ima.identity.core.application.exception.tenant;

import br.com.imarui.ima.identity.core.domain.model.tenant.TenantCode;
import lombok.Getter;

@Getter
public final class TenantCodeAlreadyExistsException
        extends RuntimeException {

    private final TenantCode tenantCode;

    public TenantCodeAlreadyExistsException(TenantCode tenantCode) {
        super(
                "Tenant with code "
                        + tenantCode.value()
                        + " already exists."
        );
        this.tenantCode = tenantCode;
    }
}
