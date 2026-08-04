package br.com.imarui.identity.identity.core.application.usecase.tenant;

import br.com.imarui.identity.identity.core.application.command.tenant.ActivateTenantCommand;
import br.com.imarui.identity.identity.core.application.exception.tenant.TenantNotFoundException;
import br.com.imarui.identity.identity.core.application.result.tenant.TenantResult;
import br.com.imarui.identity.identity.core.domain.model.tenant.Tenant;
import br.com.imarui.identity.identity.core.domain.model.tenant.TenantId;
import br.com.imarui.identity.identity.core.repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;

@Service
@RequiredArgsConstructor
public class ActivateTenantUseCase {

    private final TenantRepository tenantRepository;
    private final Clock clock;

    @Transactional
    public TenantResult execute(ActivateTenantCommand command) {
        TenantId tenantId = command.tenantId();
        Tenant tenant = tenantRepository
                .findByIdForUpdate(tenantId)
                .orElseThrow(
                        () -> new TenantNotFoundException(
                                tenantId
                        )
                );

        tenant.activate(clock.instant());

        Tenant savedTenant = tenantRepository.save(tenant);

        return TenantResult.from(savedTenant);
    }
}