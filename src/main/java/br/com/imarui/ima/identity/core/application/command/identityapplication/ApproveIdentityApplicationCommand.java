package br.com.imarui.ima.identity.core.application.command.identityapplication;

import br.com.imarui.ima.identity.core.domain.model.identityapplication.IdentityApplicationId;
import br.com.imarui.ima.identity.core.domain.model.tenant.TenantId;

public record ApproveIdentityApplicationCommand(
        IdentityApplicationId identityApplicationId,
        TenantId tenantId
) {
}
