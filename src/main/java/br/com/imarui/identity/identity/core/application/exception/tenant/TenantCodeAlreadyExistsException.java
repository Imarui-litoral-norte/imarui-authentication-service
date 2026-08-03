package br.com.imarui.identity.identity.core.application.exception.tenant;

import br.com.imarui.identity.identity.core.domain.model.tenant.TenantCode;

public final class TenantCodeAlreadyExistsException
        extends RuntimeException {

    public TenantCodeAlreadyExistsException(TenantCode code) {
        super(
                "Tenant with code "
                        + code.value()
                        + " already exists."
        );
    }
}