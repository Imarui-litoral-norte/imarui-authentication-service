package br.com.imarui.identity.identity.infra.persistence.entity.affiliation;

import br.com.imarui.identity.identity.core.domain.enums.affiliation.AffiliationStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Getter
@Entity
@Table(
        name = "affiliations",
        indexes = {
                @Index(name = "idx_affiliations_tenant", columnList = "tenant_id"),
                @Index(name = "idx_affiliations_identity", columnList = "identity_id"),
                @Index(name = "idx_affiliations_status", columnList = "status")
        }
)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(
        name = "type",
        discriminatorType = DiscriminatorType.STRING,
        length = 30
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AffiliationEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "tenant_id", nullable = false, updatable = false)
    private UUID tenantId;

    @Column(name = "identity_id", nullable = false, updatable = false)
    private UUID identityId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private AffiliationStatus status;

    @Column(name = "started_at", nullable = false, updatable = false)
    private Instant startedAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(name = "ended_at")
    private Instant endedAt;

    @Version
    @Column(name = "version", nullable = false)
    private long version;

    protected AffiliationEntity(
            UUID id,
            UUID tenantId,
            UUID identityId,
            AffiliationStatus status,
            Instant startedAt,
            Instant updatedAt,
            Instant endedAt
    ) {
        this.id = id;
        this.tenantId = tenantId;
        this.identityId = identityId;
        this.status = status;
        this.startedAt = startedAt;
        this.updatedAt = updatedAt;
        this.endedAt = endedAt;
    }

    protected final void synchronizeBaseState(
            AffiliationStatus status,
            Instant updatedAt,
            Instant endedAt
    ) {
        this.status = status;
        this.updatedAt = updatedAt;
        this.endedAt = endedAt;
    }
}
