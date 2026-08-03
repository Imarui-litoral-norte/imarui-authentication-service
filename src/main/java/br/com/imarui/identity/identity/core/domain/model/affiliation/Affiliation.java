package br.com.imarui.identity.identity.core.domain.model.affiliation;

import br.com.imarui.identity.identity.core.domain.enums.affiliation.AffiliationStatus;
import br.com.imarui.identity.identity.core.domain.enums.affiliation.AffiliationType;
import br.com.imarui.identity.identity.core.domain.exception.affiliation.AffiliationAlreadyActiveException;
import br.com.imarui.identity.identity.core.domain.exception.affiliation.AffiliationAlreadyInactiveException;
import br.com.imarui.identity.identity.core.domain.exception.affiliation.AffiliationEndBeforeStartException;
import br.com.imarui.identity.identity.core.domain.exception.affiliation.InvalidAffiliationStateException;
import br.com.imarui.identity.identity.core.domain.model.identity.IdentityId;
import br.com.imarui.identity.identity.core.domain.model.tenant.TenantId;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.Objects;

@Getter
public abstract class Affiliation {

    private final AffiliationId id;
    private final TenantId tenantId;
    private final IdentityId identityId;
    private AffiliationStatus status;
    private final Instant startedAt;
    private Instant updatedAt;
    private Instant endedAt;

    protected Affiliation(
            AffiliationId id,
            TenantId tenantId,
            IdentityId identityId,
            AffiliationStatus status,
            Instant startedAt,
            Instant updatedAt,
            Instant endedAt
    ) {
        this.id = Objects.requireNonNull(
                id,
                "id cannot be null"
        );

        this.tenantId = Objects.requireNonNull(
                tenantId,
                "tenantId cannot be null"
        );

        this.identityId = Objects.requireNonNull(
                identityId,
                "identityId cannot be null"
        );

        this.status = Objects.requireNonNull(
                status,
                "status cannot be null"
        );

        this.startedAt = Objects.requireNonNull(
                startedAt,
                "startedAt cannot be null"
        );

        this.updatedAt = Objects.requireNonNull(
                updatedAt,
                "updatedAt cannot be null"
        );

        this.endedAt = endedAt;

        validateState();
    }

    /**
     * Retorna o tipo fixo da especialização.
     *
     * <p>O tipo não é armazenado como campo para evitar estados
     * inconsistentes, como uma EmployeeAffiliation marcada como CUSTOMER.</p>
     */
    @NotNull
    public abstract AffiliationType getType();

    public final boolean isActive() {
        return status == AffiliationStatus.ACTIVE;
    }

    public final boolean isInactive() {
        return status == AffiliationStatus.INACTIVE;
    }

    /**
     * Desativa temporariamente o vínculo.
     *
     * <p>A identidade e o tenant vinculados permanecem os mesmos.</p>
     */
    public final void deactivate(@NotNull Instant now) {
        Objects.requireNonNull(now, "now cannot be null");
        validateEventTime(now);

        if (isInactive()) {
            throw new AffiliationAlreadyInactiveException(
                    id.toString()
            );
        }

        status = AffiliationStatus.INACTIVE;
        endedAt = now;
        updatedAt = now;
    }

    /**
     * Reativa uma afiliação anteriormente desativada.
     *
     * <p>O startedAt original é preservado. O endedAt é removido,
     * pois representa apenas o encerramento atualmente vigente.</p>
     */
    public final void reactivate(@NotNull Instant now) {
        Objects.requireNonNull(now, "now cannot be null");
        validateEventTime(now);

        if (isActive()) {
            throw new AffiliationAlreadyActiveException(
                    id.toString()
            );
        }

        status = AffiliationStatus.ACTIVE;
        endedAt = null;
        updatedAt = now;
    }

    /**
     * Valida a consistência do estado durante criação e reconstrução.
     */
    private void validateState() {
        if (updatedAt.isBefore(startedAt)) {
            throw new InvalidAffiliationStateException(
                    "updatedAt cannot be before startedAt."
            );
        }

        if (endedAt != null && endedAt.isBefore(startedAt)) {
            throw new AffiliationEndBeforeStartException(
                    startedAt,
                    endedAt
            );
        }

        if (endedAt != null && endedAt.isAfter(updatedAt)) {
            throw new InvalidAffiliationStateException(
                    "endedAt cannot be after updatedAt."
            );
        }

        if (isActive() && endedAt != null) {
            throw new InvalidAffiliationStateException(
                    "An active affiliation cannot have endedAt."
            );
        }

        if (isInactive() && endedAt == null) {
            throw new InvalidAffiliationStateException(
                    "An inactive affiliation must have endedAt."
            );
        }
    }

    /**
     * Impede que uma alteração seja registrada antes da última atualização.
     */
    private void validateEventTime(Instant now) {
        if (now.isBefore(updatedAt)) {
            throw new IllegalArgumentException(
                    "Event time cannot be before updatedAt."
            );
        }
    }
}