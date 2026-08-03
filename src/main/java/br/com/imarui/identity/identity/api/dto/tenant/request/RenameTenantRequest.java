package br.com.imarui.identity.identity.api.dto.tenant.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RenameTenantRequest(

        @NotBlank(message = "Tenant name is required.")
        @Size(
                max = 150,
                message = "Tenant name must not exceed 150 characters."
        )
        String name
) {
}
