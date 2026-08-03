package br.com.imarui.identity.identity.core.domain.model.tenant;

import br.com.imarui.identity.identity.core.domain.enums.tenant.TenantStatus;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.util.Objects;

@Getter
public final class Tenant {

    private final TenantId id;
    private final TenantCode code;
    private TenantName name;
    private TenantStatus status;
    private final Instant createdAt;
    private Instant updatedAt;
    private Instant activatedAt;
    private Instant disabledAt;

    private Tenant(
            TenantId id,
            TenantCode code,
            TenantName name,
            TenantStatus status,
            Instant createdAt,
            Instant updatedAt,
            Instant activatedAt,
            Instant disabledAt
    ) {
        this.id = Objects.requireNonNull(id, "id cannot be null");
        this.code = Objects.requireNonNull(code, "code cannot be null");
        this.name = Objects.requireNonNull(name, "name cannot be null");
        this.status = Objects.requireNonNull(status, "status cannot be null");
        this.createdAt = Objects.requireNonNull(
                createdAt,
                "createdAt cannot be null"
        );
        this.updatedAt = Objects.requireNonNull(
                updatedAt,
                "updatedAt cannot be null"
        );
        this.activatedAt = activatedAt;
        this.disabledAt = disabledAt;

        validateState();
    }

    public static Tenant create(
            @NotNull TenantId id,
            @NotNull TenantCode code,
            @NotNull TenantName name,
            @NotNull Instant now
    ) {
        Objects.requireNonNull(now, "now cannot be null");

        return new Tenant(
                id,
                code,
                name,
                TenantStatus.PENDING,
                now,
                now,
                null,
                null
        );
    }

    public static Tenant reconstitute(
            @NotNull TenantId id,
            @NotNull TenantCode code,
            @NotNull TenantName name,
            @NotNull TenantStatus status,
            @NotNull Instant createdAt,
            @NotNull Instant updatedAt,
            @Nullable Instant activatedAt,
            @Nullable Instant disabledAt
    ) {
        return new Tenant(
                id,
                code,
                name,
                status,
                createdAt,
                updatedAt,
                activatedAt,
                disabledAt
        );
    }

    public void activate(@NotNull Instant now) {
        Objects.requireNonNull(now, "now cannot be null");
        validateEventTime(now);

        if (status != TenantStatus.PENDING) {
            throw new IllegalStateException(
                    "Only a pending tenant can be activated."
            );
        }

        status = TenantStatus.ACTIVE;
        activatedAt = now;
        disabledAt = null;
        updatedAt = now;
    }

    public void disable(@NotNull Instant now) {
        Objects.requireNonNull(now, "now cannot be null");
        validateEventTime(now);

        if (status != TenantStatus.ACTIVE) {
            throw new IllegalStateException(
                    "Only an active tenant can be disabled."
            );
        }

        status = TenantStatus.DISABLED;
        disabledAt = now;
        updatedAt = now;
    }

    public void reactivate(@NotNull Instant now) {
        Objects.requireNonNull(now, "now cannot be null");
        validateEventTime(now);

        if (status != TenantStatus.DISABLED) {
            throw new IllegalStateException(
                    "Only a disabled tenant can be reactivated."
            );
        }

        status = TenantStatus.ACTIVE;
        disabledAt = null;
        updatedAt = now;
    }

    public void rename(
            @NotNull TenantName newName,
            @NotNull Instant now
    ) {
        Objects.requireNonNull(newName, "newName cannot be null");
        Objects.requireNonNull(now, "now cannot be null");
        validateEventTime(now);

        if (name.equals(newName)) {
            return;
        }

        name = newName;
        updatedAt = now;
    }

    public boolean isPending() {
        return status == TenantStatus.PENDING;
    }

    public boolean isActive() {
        return status == TenantStatus.ACTIVE;
    }

    public boolean isDisabled() {
        return status == TenantStatus.DISABLED;
    }

    public boolean canProvisionIdentities() {
        return isActive();
    }

    public boolean canAcceptAffiliations() {
        return isActive();
    }

    private void validateState() {
        if (updatedAt.isBefore(createdAt)) {
            throw new IllegalStateException(
                    "updatedAt cannot be before createdAt."
            );
        }

        if (activatedAt != null && activatedAt.isBefore(createdAt)) {
            throw new IllegalStateException(
                    "activatedAt cannot be before createdAt."
            );
        }

        if (disabledAt != null && disabledAt.isBefore(createdAt)) {
            throw new IllegalStateException(
                    "disabledAt cannot be before createdAt."
            );
        }

        if (activatedAt != null && activatedAt.isAfter(updatedAt)) {
            throw new IllegalStateException(
                    "activatedAt cannot be after updatedAt."
            );
        }

        if (disabledAt != null && disabledAt.isAfter(updatedAt)) {
            throw new IllegalStateException(
                    "disabledAt cannot be after updatedAt."
            );
        }

        if (
                activatedAt != null
                        && disabledAt != null
                        && disabledAt.isBefore(activatedAt)
        ) {
            throw new IllegalStateException(
                    "disabledAt cannot be before activatedAt."
            );
        }

        switch (status) {
            case PENDING -> validatePendingState();
            case ACTIVE -> validateActiveState();
            case DISABLED -> validateDisabledState();
        }
    }

    private void validatePendingState() {
        if (activatedAt != null) {
            throw new IllegalStateException(
                    "A pending tenant cannot have activatedAt."
            );
        }

        if (disabledAt != null) {
            throw new IllegalStateException(
                    "A pending tenant cannot have disabledAt."
            );
        }
    }

    private void validateActiveState() {
        if (activatedAt == null) {
            throw new IllegalStateException(
                    "An active tenant must have activatedAt."
            );
        }

        if (disabledAt != null) {
            throw new IllegalStateException(
                    "An active tenant cannot have disabledAt."
            );
        }
    }

    private void validateDisabledState() {
        if (activatedAt == null) {
            throw new IllegalStateException(
                    "A disabled tenant must have activatedAt."
            );
        }

        if (disabledAt == null) {
            throw new IllegalStateException(
                    "A disabled tenant must have disabledAt."
            );
        }
    }

    /*
     * Impede alterações registradas antes da última atualização.
     */
    private void validateEventTime(Instant now) {
        if (now.isBefore(updatedAt)) {
            throw new IllegalArgumentException(
                    "Event time cannot be before updatedAt."
            );
        }
    }


}