package br.com.imarui.ima.identity.infra.persistence.entity.identityapplication;

import br.com.imarui.ima.identity.core.domain.enums.identityapplication.IdentityApplicationStatus;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
        name = "ima_identity_applications",
        indexes = {
                @Index(
                        name = "idx_identity_applications_status_requested_at",
                        columnList = "status, requested_at"
                ),
                @Index(
                        name = "idx_identity_applications_cpf",
                        columnList = "cpf"
                )
        }
)
@Access(AccessType.FIELD)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IdentityApplicationEntity {

    @Id
    @Column(
            name = "id",
            nullable = false,
            updatable = false
    )
    private UUID id;

    @Column(
            name = "full_name",
            nullable = false,
            length = 150
    )
    private String fullName;

    @Column(
            name = "cpf",
            nullable = false,
            length = 11
    )
    private String cpf;

    @Column(
            name = "email",
            nullable = false,
            length = 180
    )
    private String email;

    @Column(
            name = "phone_number",
            nullable = false,
            length = 20
    )
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "status",
            nullable = false,
            length = 20
    )
    private IdentityApplicationStatus status;

    @Column(
            name = "requested_at",
            nullable = false,
            updatable = false
    )
    private Instant requestedAt;

    @Column(name = "reviewed_at")
    private Instant reviewedAt;

    @Column(name = "reviewed_by_identity_id")
    private UUID reviewedByIdentityId;

    @Column(
            name = "rejection_reason",
            length = 500
    )
    private String rejectionReason;

    @Column(name = "resolved_identity_id")
    private UUID resolvedIdentityId;

    @Column(name = "assigned_tenant_id")
    private UUID assignedTenantId;

    @Column(name = "resulting_affiliation_id")
    private UUID resultingAffiliationId;

    @Version
    @Column(
            name = "version",
            nullable = false
    )
    private Long version;

    public IdentityApplicationEntity(
            UUID id,
            String fullName,
            String cpf,
            String email,
            String phoneNumber,
            IdentityApplicationStatus status,
            Instant requestedAt,
            Instant reviewedAt,
            UUID reviewedByIdentityId,
            String rejectionReason,
            UUID resolvedIdentityId,
            UUID assignedTenantId,
            UUID resultingAffiliationId
    ) {
        this.id = id;
        this.fullName = fullName;
        this.cpf = cpf;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.requestedAt = requestedAt;
        this.reviewedAt = reviewedAt;
        this.reviewedByIdentityId = reviewedByIdentityId;
        this.rejectionReason = rejectionReason;
        this.resolvedIdentityId = resolvedIdentityId;
        this.assignedTenantId = assignedTenantId;
        this.resultingAffiliationId = resultingAffiliationId;
    }

    public void updateFrom(
            IdentityApplicationStatus status,
            Instant reviewedAt,
            UUID reviewedByIdentityId,
            String rejectionReason,
            UUID resolvedIdentityId,
            UUID assignedTenantId,
            UUID resultingAffiliationId
    ) {
        this.status = status;
        this.reviewedAt = reviewedAt;
        this.reviewedByIdentityId = reviewedByIdentityId;
        this.rejectionReason = rejectionReason;
        this.resolvedIdentityId = resolvedIdentityId;
        this.assignedTenantId = assignedTenantId;
        this.resultingAffiliationId = resultingAffiliationId;
    }
}