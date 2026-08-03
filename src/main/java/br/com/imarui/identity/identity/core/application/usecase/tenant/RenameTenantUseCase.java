package br.com.imarui.identity.identity.core.application.usecase.tenant;

import br.com.imarui.identity.identity.core.application.command.tenant.RenameTenantCommand;
import br.com.imarui.identity.identity.core.application.exception.tenant.TenantNotFoundException;
import br.com.imarui.identity.identity.core.application.result.tenant.TenantResult;
import br.com.imarui.identity.identity.core.domain.model.tenant.Tenant;
import br.com.imarui.identity.identity.core.domain.model.tenant.TenantId;
import br.com.imarui.identity.identity.core.domain.model.tenant.TenantName;
import br.com.imarui.identity.identity.core.repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Instant;


@Service
@RequiredArgsConstructor
public class RenameTenantUseCase {

    private final TenantRepository tenantRepository;
    private final Clock clock;

    @Transactional(readOnly = true)
    public TenantResult execute(RenameTenantCommand command) {
        TenantName name = TenantName.from(command.name());
        TenantId id = command.tenantId();
        Instant now = Instant.now(clock);

        Tenant tenant = tenantRepository
                .findById(id)
                .orElseThrow(
                        () -> new TenantNotFoundException(id)
                );

        tenant.rename(name, now);

        return TenantResult.from(tenant);
    }

}
