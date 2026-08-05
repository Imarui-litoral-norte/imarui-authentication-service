package br.com.imarui.ima.identity.core.application.usecase.identityapplication;

import br.com.imarui.ima.identity.core.application.command.identityapplication.RejectIdentityApplicationCommand;
import br.com.imarui.ima.identity.core.application.result.identityapplication.IdentityApplicationResult;
import org.springframework.stereotype.Service;

@Service
public class RejectIdentityApplicationUseCase {

    public IdentityApplicationResult execute(
            RejectIdentityApplicationCommand command
    ) {
        throw new UnsupportedOperationException("RejectIdentityApplicationUseCase is not implemented yet.");
    }
}
