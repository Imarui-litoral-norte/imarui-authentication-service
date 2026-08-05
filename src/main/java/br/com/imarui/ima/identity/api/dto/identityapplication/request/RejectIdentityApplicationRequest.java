package br.com.imarui.ima.identity.api.dto.identityapplication.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RejectIdentityApplicationRequest(
        @NotBlank @Size(max = 500) String reason
) {
}
