package br.com.imarui.ima.identity.core.application.usecase.identity;

import br.com.imarui.ima.identity.core.application.command.identity.ActivateIdentityCommand;
import br.com.imarui.ima.identity.core.application.result.identity.IdentityResult;
import org.springframework.stereotype.Service;

@Service
public class ActivateIdentityUseCase {

    public IdentityResult execute(ActivateIdentityCommand command) {
        throw new UnsupportedOperationException("ActivateIdentityUseCase is not implemented yet.");
    }
}
