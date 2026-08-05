package br.com.imarui.ima.identity.api.dto.identityapplication.request.create;

import br.com.imarui.ima.identity.api.dto.identityapplication.request.create.CreateLegalEntityIdentityApplicationRequest;
import br.com.imarui.ima.identity.api.dto.identityapplication.request.create.CreatePersonIdentityApplicationRequest;
import br.com.imarui.ima.identity.api.dto.identityapplication.request.create.CreateServiceIdentityApplicationRequest;
import br.com.imarui.ima.identity.core.domain.enums.identity.IdentityKind;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.DiscriminatorMapping;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(
                value = CreatePersonIdentityApplicationRequest.class,
                name = "PERSON"
        ),
        @JsonSubTypes.Type(
                value = CreateLegalEntityIdentityApplicationRequest.class,
                name = "LEGAL_ENTITY"
        ),
        @JsonSubTypes.Type(
                value = CreateServiceIdentityApplicationRequest.class,
                name = "SERVICE"
        )
})
@Schema(
        description = "Solicitacao de cadastro de uma identidade.",
        discriminatorProperty = "type",
        oneOf = {
                CreatePersonIdentityApplicationRequest.class,
                CreateLegalEntityIdentityApplicationRequest.class,
                CreateServiceIdentityApplicationRequest.class
        },
        discriminatorMapping = {
                @DiscriminatorMapping(
                        value = "PERSON",
                        schema = CreatePersonIdentityApplicationRequest.class
                ),
                @DiscriminatorMapping(
                        value = "LEGAL_ENTITY",
                        schema = CreateLegalEntityIdentityApplicationRequest.class
                ),
                @DiscriminatorMapping(
                        value = "SERVICE",
                        schema = CreateServiceIdentityApplicationRequest.class
                )
        }
)
public sealed interface CreateIdentityApplicationRequest
        permits CreatePersonIdentityApplicationRequest,
        CreateLegalEntityIdentityApplicationRequest,
        CreateServiceIdentityApplicationRequest {

    IdentityKind type();
}
