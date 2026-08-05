package br.com.imarui.ima.identity.core.application.command.identityapplication;

import br.com.imarui.ima.identity.core.domain.model.identityapplication.IdentityApplicationId;

public record RejectIdentityApplicationCommand(
        IdentityApplicationId identityApplicationId,
        String reason
) {
}
