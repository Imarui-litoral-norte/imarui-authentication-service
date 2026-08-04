package br.com.imarui.identity.identity.core.application.command.tenant;

import br.com.imarui.identity.identity.core.domain.model.tenant.TenantId;

public record ActivateTenantCommand(
        TenantId tenantId
) {}
