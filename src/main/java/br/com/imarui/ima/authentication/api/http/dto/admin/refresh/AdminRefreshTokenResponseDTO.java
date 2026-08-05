package br.com.imarui.ima.authentication.api.http.dto.admin.refresh;

import br.com.imarui.ima.authentication.core.application.result.admin.refresh.AdminRefreshTokenResult;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Schema(name = "AdminRefreshTokenResponse", description = "Resposta administrativa com metadados de um refresh token.")
public record AdminRefreshTokenResponseDTO(
        @Schema(description = "Identificador único do refresh token.", example = "50", requiredMode = Schema.RequiredMode.REQUIRED)
        Long id,
        @Schema(description = "Identificador da sessão vinculada ao refresh token.", example = "10", requiredMode = Schema.RequiredMode.REQUIRED)
        Long sessionId,
        @Schema(description = "Status atual do refresh token.", example = "ACTIVE", requiredMode = Schema.RequiredMode.REQUIRED)
        String status,
        @Schema(description = "Data de criação do refresh token.", example = "2026-06-04T17:44:38Z", requiredMode = Schema.RequiredMode.REQUIRED)
        Instant createdAt,
        @Schema(description = "Data de expiração do refresh token.", example = "2026-07-04T17:44:38Z", requiredMode = Schema.RequiredMode.REQUIRED)
        Instant expiresAt,
        @Schema(description = "Identificador do refresh token que o substituiu após uma rotação.", example = "51", nullable = true)
        Long replacedByTokenId
) {

    public static AdminRefreshTokenResponseDTO from(AdminRefreshTokenResult result) {
        Objects.requireNonNull(result, "result cannot be null");
        return new AdminRefreshTokenResponseDTO(
                result.id(),
                result.sessionId(),
                result.status(),
                result.createdAt(),
                result.expiresAt(),
                result.replacedByTokenId()
        );
    }

    public static List<AdminRefreshTokenResponseDTO> from(List<AdminRefreshTokenResult> results) {
        Objects.requireNonNull(results, "results cannot be null");
        return results.stream().map(AdminRefreshTokenResponseDTO::from).toList();
    }
}
