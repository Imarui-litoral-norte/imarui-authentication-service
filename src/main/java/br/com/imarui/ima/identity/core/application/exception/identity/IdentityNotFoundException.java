package br.com.imarui.ima.identity.core.application.exception.identity;

import br.com.imarui.ima.identity.core.domain.model.identity.IdentityId;
import lombok.Getter;

@Getter
public final class IdentityNotFoundException
        extends RuntimeException {

    private final IdentityId identityId;

    public IdentityNotFoundException(
            IdentityId identityId
    ) {
        super(
                "Identity with id "
                        + identityId.value()
                        + " was not found."
        );
        this.identityId = identityId;
    }
}