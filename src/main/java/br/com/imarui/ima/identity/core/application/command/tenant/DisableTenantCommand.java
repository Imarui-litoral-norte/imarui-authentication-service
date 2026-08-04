package br.com.imarui.ima.identity.core.application.command.tenant;

import br.com.imarui.ima.identity.core.domain.model.tenant.TenantId;

public record DisableTenantCommand(
        TenantId tenantId
) {
}
