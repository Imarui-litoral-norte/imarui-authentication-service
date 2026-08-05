package br.com.imarui.ima.identity.infra.persistence.entity.affiliation;

import br.com.imarui.ima.identity.core.domain.enums.affiliation.AffiliationStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "ima_external_affiliations")
@DiscriminatorValue("EXTERNAL")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExternalAffiliationEntity extends AffiliationEntity {

    public ExternalAffiliationEntity(
            UUID id,
            UUID tenantId,
            UUID identityId,
            AffiliationStatus status,
            Instant startedAt,
            Instant updatedAt,
            Instant endedAt
    ) {
        super(id, tenantId, identityId, status, startedAt, updatedAt, endedAt);
    }

    public void synchronize(
            AffiliationStatus status,
            Instant updatedAt,
            Instant endedAt
    ) {
        synchronizeBaseState(status, updatedAt, endedAt);
    }
}
