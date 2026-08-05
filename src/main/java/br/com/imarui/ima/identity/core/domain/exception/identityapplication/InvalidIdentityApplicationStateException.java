package br.com.imarui.ima.identity.core.domain.exception.identityapplication;

import lombok.Getter;

@Getter
public final class InvalidIdentityApplicationStateException
        extends RuntimeException {

    private final String reason;

    public InvalidIdentityApplicationStateException(
            String reason
    ) {
        super(
                "Invalid identity application state: "
                        + reason
        );
        this.reason = reason;
    }
}