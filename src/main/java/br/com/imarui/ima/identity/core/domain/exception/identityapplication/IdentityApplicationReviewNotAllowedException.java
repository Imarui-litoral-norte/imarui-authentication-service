package br.com.imarui.ima.identity.core.domain.exception.identityapplication;

import br.com.imarui.ima.identity.core.domain.enums.identityapplication.IdentityApplicationStatus;
import br.com.imarui.ima.identity.core.domain.model.identityapplication.IdentityApplicationId;

import lombok.Getter;

@Getter
public final class IdentityApplicationReviewNotAllowedException
        extends RuntimeException {

    private final IdentityApplicationId identityApplicationId;
    private final IdentityApplicationStatus currentStatus;

    public IdentityApplicationReviewNotAllowedException(
            IdentityApplicationId identityApplicationId,
            IdentityApplicationStatus currentStatus
    ) {
        super(
                "Identity application with id "
                        + identityApplicationId.value()
                        + " cannot be reviewed while status is "
                        + currentStatus
                        + "."
        );
        this.identityApplicationId = identityApplicationId;
        this.currentStatus = currentStatus;
    }
}