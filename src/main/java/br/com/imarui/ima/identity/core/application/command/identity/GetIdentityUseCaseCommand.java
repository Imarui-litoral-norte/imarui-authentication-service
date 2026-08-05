package br.com.imarui.ima.identity.core.application.command.identity;

import br.com.imarui.ima.identity.core.domain.model.identity.IdentityId;

public record GetIdentityUseCaseCommand(
        IdentityId identityId
) {
}
