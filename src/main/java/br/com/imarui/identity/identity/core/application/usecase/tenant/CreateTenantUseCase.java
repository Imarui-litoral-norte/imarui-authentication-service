package br.com.imarui.identity.identity.core.application.usecase.tenant;

import br.com.imarui.identity.identity.core.application.command.tenant.CreateTenantCommand;
import br.com.imarui.identity.identity.core.application.exception.tenant.TenantCodeAlreadyExistsException;
import br.com.imarui.identity.identity.core.application.result.tenant.TenantResult;
import br.com.imarui.identity.identity.core.domain.model.tenant.Tenant;
import br.com.imarui.identity.identity.core.domain.model.tenant.TenantCode;
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
public class CreateTenantUseCase {

    private final TenantRepository tenantRepository;
    private final Clock clock;

    @Transactional
    public TenantResult execute(
            CreateTenantCommand command
    ) {
        TenantCode code = TenantCode.from(command.code());
        TenantName name = TenantName.from(command.name());

        if (tenantRepository.existsByCode(code)) {
            throw new TenantCodeAlreadyExistsException(code);
        }
        Instant now = clock.instant();

        Tenant tenant = Tenant.create(
                TenantId.generate(),
                code,
                name,
                now
        );

        Tenant savedTenant = tenantRepository.save(tenant);

        return TenantResult.from(savedTenant);
    }
}