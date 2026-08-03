package br.com.imarui.identity.identity.api.controller.identity;

import br.com.imarui.identity.authentication.api.http.dto.token.AuthTokenResponseDTO;
import br.com.imarui.identity.identity.api.dto.user.UserRegistrationRequestDTO;
import br.com.imarui.identity.authentication.core.application.result.AuthTokens;
import br.com.imarui.identity.identity.core.application.service.user.UserService;
import br.com.imarui.identity.platform.openapi.group.SwaggerOperationGroup;
import br.com.imarui.identity.platform.web.exception.ApiErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        value = "/authentication",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
@Validated
@Tag(
        name = "Authentication - Users",
        description = "Operações públicas e administrativas para cadastro, consulta e gerenciamento de usuários."
)
public class IdentityController {

    private final UserService userService;

    @PostMapping(
            value = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @SwaggerOperationGroup(value = "Rotas públicas", order = 10)
    @Operation(
            summary = "Registrar usuário",
            description = """
                    Cria uma nova conta de usuário a partir dos dados cadastrais informados.
                    Após o registro, autentica o usuário criado e retorna access token e refresh token.
                    """,
            security = {},
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Usuário registrado e autenticado com sucesso.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AuthTokenResponseDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Requisição inválida ou dados cadastrais inconsistentes.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiErrorResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Já existe usuário cadastrado com os dados únicos informados.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiErrorResponse.class)
                            )
                    )
            }
    )
    public ResponseEntity<AuthTokenResponseDTO> register(
            @RequestBody @Valid UserRegistrationRequestDTO request
    ) {
        AuthTokens tokens = userService.registerAndAuthenticate(
                request.name(),
                request.birthDate(),
                request.email(),
                request.cpf(),
                request.rg(),
                request.password(),
                request.phoneNumber()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AuthTokenResponseDTO.from(tokens));
    }
}
