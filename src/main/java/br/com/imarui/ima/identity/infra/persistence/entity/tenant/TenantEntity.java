package br.com.imarui.ima.identity.infra.persistence.entity.tenant;

import br.com.imarui.ima.identity.core.domain.enums.tenant.TenantStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Getter
@Entity
@Table(
        name = "ima_tenants",
        indexes = {
                @Index(name = "idx_tenants_code", columnList = "code"),
                @Index(name = "idx_tenants_status", columnList = "status")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_tenants_code", columnNames = "code")
        }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TenantEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "code", nullable = false, updatable = false, length = 50)
    private String code;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private TenantStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(name = "activated_at")
    private Instant activatedAt;

    @Column(name = "disabled_at")
    private Instant disabledAt;

    @Version
    @Column(name = "version", nullable = false)
    private long version;

    public TenantEntity(
            UUID id,
            String code,
            String name,
            TenantStatus status,
            Instant createdAt,
            Instant updatedAt,
            Instant activatedAt,
            Instant disabledAt
    ) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.activatedAt = activatedAt;
        this.disabledAt = disabledAt;
    }

    public void synchronize(
            String name,
            TenantStatus status,
            Instant updatedAt,
            Instant activatedAt,
            Instant disabledAt
    ) {
        this.name = name;
        this.status = status;
        this.updatedAt = updatedAt;
        this.activatedAt = activatedAt;
        this.disabledAt = disabledAt;
    }
}
