package br.com.imarui.identity.identity.api.controller.tenant;

import br.com.imarui.identity.identity.api.dto.tenant.request.RenameTenantRequest;
import br.com.imarui.identity.identity.api.dto.tenant.response.TenantResponse;
import br.com.imarui.identity.identity.core.application.command.tenant.RenameTenantCommand;
import br.com.imarui.identity.identity.core.application.result.tenant.TenantResult;
import br.com.imarui.identity.identity.core.application.usecase.tenant.GetTenantUseCase;
import br.com.imarui.identity.identity.core.application.usecase.tenant.RenameTenantUseCase;
import br.com.imarui.identity.identity.core.domain.model.tenant.TenantId;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tenants")
@RequiredArgsConstructor
public class TenantController {

    private final GetTenantUseCase getTenantUseCase;
    private final RenameTenantUseCase renameTenantUseCase;

    @GetMapping("/{tenantId}")
    public ResponseEntity<TenantResponse> findById(
            @PathVariable String tenantId
    ) {
        TenantId id = TenantId.from(tenantId);

        TenantResult result = getTenantUseCase.execute(id);

        return ResponseEntity.ok(
                TenantResponse.from(result)
        );
    }


}