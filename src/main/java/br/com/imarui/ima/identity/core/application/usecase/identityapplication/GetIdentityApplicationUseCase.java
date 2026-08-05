package br.com.imarui.ima.identity.core.application.usecase.identityapplication;

import br.com.imarui.ima.identity.core.application.result.identityapplication.IdentityApplicationResult;
import br.com.imarui.ima.identity.core.domain.model.identityapplication.IdentityApplicationId;
import org.springframework.stereotype.Service;

@Service
public class GetIdentityApplicationUseCase {

    public IdentityApplicationResult execute(
            IdentityApplicationId identityApplicationId
    ) {
        throw new UnsupportedOperationException("GetIdentityApplicationUseCase is not implemented yet.");
    }
}
