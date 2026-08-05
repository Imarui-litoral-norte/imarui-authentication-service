package br.com.imarui.ima.identity.core.application.usecase.identityapplication;

import br.com.imarui.ima.identity.core.application.command.identityapplication.CreateIdentityApplicationCommand;
import br.com.imarui.ima.identity.core.application.result.identityapplication.IdentityApplicationResult;
import br.com.imarui.ima.identity.core.domain.enums.identity.EmailType;
import br.com.imarui.ima.identity.core.domain.model.identity.Email;
import br.com.imarui.ima.identity.core.domain.model.identity.IdentityEmail;
import br.com.imarui.ima.identity.core.domain.model.identityapplication.IdentityApplication;
import br.com.imarui.ima.identity.core.domain.model.identityapplication.IdentityApplicationId;
import br.com.imarui.ima.identity.core.repository.IdentityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class CreateIdentityApplicationUseCase {

    private final Clock clock;
    private final IdentityRepository identityRepository;

    public IdentityApplicationResult execute(
            CreateIdentityApplicationCommand command
    ) {
        Instant now = Instant.now(clock);
        IdentityApplication.create(
                IdentityApplicationId.generate(),
                command.fullName(),
                command.cpf(),
                IdentityEmail.create(
                        Email.from(command.email()),
                        EmailType.PRIMARY, now),
                command.phoneNumber(),
                now
        )


    }
}
