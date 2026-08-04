package br.com.imarui.identity.identity.api.controller.tenant;

import br.com.imarui.identity.identity.api.dto.tenant.request.CreateTenantRequest;
import br.com.imarui.identity.identity.api.dto.tenant.request.RenameTenantRequest;
import br.com.imarui.identity.identity.api.dto.tenant.response.TenantResponse;
import br.com.imarui.identity.identity.core.application.command.tenant.ActivateTenantCommand;
import br.com.imarui.identity.identity.core.application.command.tenant.CreateTenantCommand;
import br.com.imarui.identity.identity.core.application.command.tenant.RenameTenantCommand;
import br.com.imarui.identity.identity.core.application.result.tenant.TenantResult;
import br.com.imarui.identity.identity.core.application.usecase.tenant.*;
import br.com.imarui.identity.identity.core.domain.model.tenant.TenantId;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/admin/tenants")
@RequiredArgsConstructor
public class AdminTenantController {

    private final RenameTenantUseCase renameTenantUseCase;
    private final ActivateTenantUseCase activateTenantUseCase;
    private final DisableTenantUseCase disableTenantUseCase;
    private final ReactivateTenantUseCase reactivateTenantUseCase;
    private final CreateTenantUseCase createTenantUseCase;

    @PostMapping
    public ResponseEntity<TenantResponse> create(
            @Valid @RequestBody CreateTenantRequest request,
            UriComponentsBuilder uriBuilder
    ) {
        CreateTenantCommand command = new CreateTenantCommand(
                request.code(),
                request.name()
        );

        TenantResult result = createTenantUseCase.execute(command);

        return ResponseEntity
                .created(
                        uriBuilder
                                .path("/api/v1/tenants/{tenantId}")
                                .buildAndExpand(result.id())
                                .toUri()
                )
                .body(TenantResponse.from(result));
    }

    @PostMapping("/{tenantId}/activate")
    public ResponseEntity<TenantResponse> activate(
            @PathVariable String tenantId
    ) {
        ActivateTenantCommand command = new ActivateTenantCommand(
                TenantId.from(tenantId)
        );

        TenantResult result = activateTenantUseCase.execute(command);

        return ResponseEntity.ok(
                TenantResponse.from(result)
        );
    }

    @PostMapping("/{tenantId}/disable")
    public ResponseEntity<TenantResponse> disable(
            @PathVariable String tenantId
    ) {
        TenantId id = TenantId.from(tenantId);

        TenantResult result = disableTenantUseCase.execute(id);

        return ResponseEntity.ok(
                TenantResponse.from(result)
        );
    }

    @PostMapping("/{tenantId}/reactivate")
    public ResponseEntity<TenantResponse> reactivate(
            @PathVariable String tenantId
    ) {
        TenantId id = TenantId.from(tenantId);

        TenantResult result = reactivateTenantUseCase.execute(id);

        return ResponseEntity.ok(
                TenantResponse.from(result)
        );
    }

    @PatchMapping("/{tenantId}/name")
    public ResponseEntity<TenantResponse> rename(
            @PathVariable String tenantId,
            @Valid @RequestBody RenameTenantRequest request
    ) {
        RenameTenantCommand command = new RenameTenantCommand(
                TenantId.from(tenantId),
                request.name()
        );

        TenantResult result = renameTenantUseCase.execute(command);

        return ResponseEntity.ok(
                TenantResponse.from(result)
        );
    }
}