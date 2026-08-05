package br.com.imarui.ima.identity.infra.persistence.entity.identity;

import br.com.imarui.ima.identity.core.domain.enums.identity.IdentityStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Getter
@Entity
@Table(
        name = "ima_legal_entity_identities",
        indexes = {
                @Index(name = "idx_legal_entity_identities_cnpj", columnList = "cnpj")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_legal_entity_identities_cnpj", columnNames = "cnpj")
        }
)
@DiscriminatorValue("LEGAL_ENTITY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LegalEntityIdentityEntity extends IdentityEntity {

    @Column(name = "legal_name", nullable = false, length = 150)
    private String legalName;

    @Column(name = "trade_name", length = 150)
    private String tradeName;

    @Column(name = "cnpj", nullable = false, updatable = false, length = 14)
    private String cnpj;

    public LegalEntityIdentityEntity(
            UUID id,
            IdentityEmailEmbeddable primaryEmail,
            String legalName,
            String tradeName,
            String cnpj,
            IdentityStatus status,
            Instant createdAt,
            Instant updatedAt,
            Instant activatedAt,
            Instant disabledAt
    ) {
        super(
                id,
                primaryEmail,
                status,
                createdAt,
                updatedAt,
                activatedAt,
                disabledAt
        );
        this.legalName = legalName;
        this.tradeName = tradeName;
        this.cnpj = cnpj;
    }

    public void synchronize(
            IdentityEmailEmbeddable primaryEmail,
            String legalName,
            String tradeName,
            IdentityStatus status,
            Instant updatedAt,
            Instant activatedAt,
            Instant disabledAt
    ) {
        synchronizeBaseState(
                primaryEmail,
                status,
                updatedAt,
                activatedAt,
                disabledAt
        );
        this.legalName = legalName;
        this.tradeName = tradeName;
    }
}
