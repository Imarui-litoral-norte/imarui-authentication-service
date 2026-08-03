package br.com.imarui.identity.identity.infra.persistence.entity.affiliation;

import br.com.imarui.identity.identity.core.domain.enums.affiliation.AffiliationStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "partner_affiliations")
@DiscriminatorValue("PARTNER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PartnerAffiliationEntity extends AffiliationEntity {

    public PartnerAffiliationEntity(
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
