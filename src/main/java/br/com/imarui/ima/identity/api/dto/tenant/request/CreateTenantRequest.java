package br.com.imarui.ima.identity.api.dto.tenant.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTenantRequest(

        @NotBlank(message = "Tenant code is required.")
        @Size(
                min = 3,
                max = 50,
                message = "Tenant code must contain between 3 and 50 characters."
        )
        String code,

        @NotBlank(message = "Tenant name is required.")
        @Size(
                max = 150,
                message = "Tenant name must not exceed 150 characters."
        )
        String name
) {
}
