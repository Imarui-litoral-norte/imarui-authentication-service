package br.com.imarui.ima.identity.api.factory;

import br.com.imarui.ima.identity.api.dto.identityapplication.request.create.CreateIdentityApplicationRequest;
import br.com.imarui.ima.identity.api.dto.identityapplication.request.create.CreatePersonIdentityApplicationRequest;
import br.com.imarui.ima.identity.core.application.command.identityapplication.CreateIdentityApplicationCommand;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public final class IdentityApplicationCommandFactory {

    public CreateIdentityApplicationCommand from(
            CreateIdentityApplicationRequest request
    ) {
        Objects.requireNonNull(
                request,
                "request cannot be null"
        );

        return switch (request) {
            case CreatePersonIdentityApplicationRequest person ->
                    new CreatePersonIdentityApplicationCommand(
                            person.fullName(),
                            person.cpf(),
                            person.email(),
                            person.phoneNumber()
                    );

            case CreateCompanyIdentityApplicationRequest company ->
                    new CreateCompanyIdentityApplicationCommand(
                            company.corporateName(),
                            company.tradeName(),
                            company.cnpj(),
                            company.email(),
                            company.phoneNumber()
                    );

            case CreateEmployeeIdentityApplicationRequest employee ->
                    new CreateEmployeeIdentityApplicationCommand(
                            employee.fullName(),
                            employee.cpf(),
                            employee.email(),
                            employee.phoneNumber(),
                            employee.employeeCode()
                    );
        };
    }
}