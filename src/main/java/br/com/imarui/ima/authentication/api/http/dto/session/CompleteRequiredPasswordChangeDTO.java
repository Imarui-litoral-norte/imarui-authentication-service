package br.com.imarui.ima.authentication.api.http.dto.session;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CompleteRequiredPasswordChangeDTO(

        @NotBlank
        String passwordChangeToken,

        @NotBlank
        @Size(min = 8, max = 72)
        String newPassword
) {
}