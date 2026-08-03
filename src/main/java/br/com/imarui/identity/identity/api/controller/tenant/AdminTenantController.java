package br.com.imarui.identity.identity.api.controller.tenant;

import br.com.imarui.identity.identity.api.dto.tenant.request.CreateTenantRequest;
import br.com.imarui.identity.identity.api.dto.tenant.response.TenantResponse;
import br.com.imarui.identity.identity.core.application.command.tenant.CreateTenantCommand;
import br.com.imarui.identity.identity.core.application.result.tenant.TenantResult;
import br.com.imarui.identity.identity.core.application.service.tenant.ActivateTenantService;
import br.com.imarui.identity.identity.core.application.service.tenant.CreateTenantService;
import br.com.imarui.identity.identity.core.application.service.tenant.DisableTenantService;
import br.com.imarui.identity.identity.core.application.service.tenant.ReactivateTenantService;
import br.com.imarui.identity.identity.core.domain.model.tenant.TenantId;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/admin/tenants")
@RequiredArgsConstructor
public class AdminTenantController {

    private final CreateTenantService createTenantService;
    private final ActivateTenantService activateTenantService;
    private final DisableTenantService disableTenantService;
    private final ReactivateTenantService reactivateTenantService;

    @PostMapping
    public ResponseEntity<TenantResponse> create(
            @Valid @RequestBody CreateTenantRequest request,
            UriComponentsBuilder uriBuilder
    ) {
        CreateTenantCommand command = new CreateTenantCommand(
                request.code(),
                request.name()
        );

        TenantResult result = createTenantService.execute(command);

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
        TenantId id = TenantId.from(tenantId);

        TenantResult result = activateTenantService.execute(id);

        return ResponseEntity.ok(
                TenantResponse.from(result)
        );
    }

    @PostMapping("/{tenantId}/disable")
    public ResponseEntity<TenantResponse> disable(
            @PathVariable String tenantId
    ) {
        TenantId id = TenantId.from(tenantId);

        TenantResult result = disableTenantService.execute(id);

        return ResponseEntity.ok(
                TenantResponse.from(result)
        );
    }

    @PostMapping("/{tenantId}/reactivate")
    public ResponseEntity<TenantResponse> reactivate(
            @PathVariable String tenantId
    ) {
        TenantId id = TenantId.from(tenantId);

        TenantResult result = reactivateTenantService.execute(id);

        return ResponseEntity.ok(
                TenantResponse.from(result)
        );
    }
}