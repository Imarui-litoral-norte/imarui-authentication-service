package br.com.imarui.ima.identity.core.application.usecase.identity;

import br.com.imarui.ima.identity.core.application.command.identity.ReactivateIdentityCommand;
import br.com.imarui.ima.identity.core.application.result.identity.IdentityResult;
import org.springframework.stereotype.Service;

@Service
public class ReactivateIdentityUseCase {

    public IdentityResult execute(ReactivateIdentityCommand command) {
        throw new UnsupportedOperationException("ReactivateIdentityUseCase is not implemented yet.");
    }
}
