package br.com.imarui.ima.identity.core.application.usecase.identity;

import br.com.imarui.ima.identity.core.application.command.identity.DisableIdentityCommand;
import br.com.imarui.ima.identity.core.application.result.identity.IdentityResult;
import org.springframework.stereotype.Service;

@Service
public class DisableIdentityUseCase {

    public IdentityResult execute(DisableIdentityCommand command) {
        throw new UnsupportedOperationException("DisableIdentityUseCase is not implemented yet.");
    }
}
