package br.com.imarui.ima.identity.core.application.usecase.identityapplication;

import br.com.imarui.ima.identity.core.application.command.identityapplication.ApproveIdentityApplicationCommand;
import br.com.imarui.ima.identity.core.application.result.identityapplication.IdentityApplicationResult;
import org.springframework.stereotype.Service;

@Service
public class ApproveIdentityApplicationUseCase {

    public IdentityApplicationResult execute(
            ApproveIdentityApplicationCommand command
    ) {
        throw new UnsupportedOperationException("ApproveIdentityApplicationUseCase is not implemented yet.");
    }
}
