package br.com.imarui.ima.identity.api.dto.identityapplication.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ApproveIdentityApplicationRequest(
        @NotNull UUID tenantId
) {
}
