package br.com.imarui.identity.identity.core.domain.model;

import br.com.imarui.identity.identity.core.domain.enums.user.AffiliationStatus;
import br.com.imarui.identity.identity.core.domain.enums.user.AffiliationType;
import br.com.imarui.identity.identity.core.domain.exceptions.user.AffiliationAlreadyActiveException;
import br.com.imarui.identity.identity.core.domain.exceptions.user.AffiliationAlreadyInactiveException;
import br.com.imarui.identity.identity.core.domain.exceptions.user.AffiliationEndBeforeStartException;
import br.com.imarui.identity.identity.core.domain.exceptions.user.InvalidAffiliationStateException;
import br.com.imarui.identity.identity.core.domain.model.id.AffiliationId;
import br.com.imarui.identity.identity.core.domain.model.id.OrganizationId;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.Objects;

@Getter
public class Affiliation {

    private final AffiliationId id;
    private final OrganizationId organizationId;
    private final AffiliationType type;

    private AffiliationStatus status;

    private final Instant startedAt;
    private Instant endedAt;

    private Affiliation(
            AffiliationId id,
            OrganizationId organizationId,
            AffiliationType type,
            AffiliationStatus status,
            Instant startedAt,
            Instant endedAt
    ) {
        this.id = id;
        this.organizationId = organizationId;
        this.type = type;
        this.status = status;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }

    public static Affiliation create(
            @NotNull AffiliationId id,
            @NotNull OrganizationId organizationId,
            @NotNull AffiliationType type,
            @NotNull Instant now
    ) {
        Objects.requireNonNull(id, "id cannot be null");
        Objects.requireNonNull(
                organizationId,
                "organizationId cannot be null"
        );
        Objects.requireNonNull(type, "type cannot be null");

        validateNow(now);

        return new Affiliation(
                id,
                organizationId,
                type,
                AffiliationStatus.ACTIVE,
                now,
                null
        );
    }

    public static Affiliation reconstitute(
            @NotNull AffiliationId id,
            @NotNull OrganizationId organizationId,
            @NotNull AffiliationType type,
            @NotNull AffiliationStatus status,
            @NotNull Instant startedAt,
            Instant endedAt
    ) {
        Objects.requireNonNull(id, "id cannot be null");
        Objects.requireNonNull(
                organizationId,
                "organizationId cannot be null"
        );
        Objects.requireNonNull(type, "type cannot be null");
        Objects.requireNonNull(status, "status cannot be null");
        Objects.requireNonNull(startedAt, "startedAt cannot be null");

        validateState(status, startedAt, endedAt);

        return new Affiliation(
                id,
                organizationId,
                type,
                status,
                startedAt,
                endedAt
        );
    }

    public boolean isActive() {
        return status == AffiliationStatus.ACTIVE;
    }

    public boolean isInactive() {
        return status == AffiliationStatus.INACTIVE;
    }

    public void deactivate(@NotNull Instant now) {
        validateNow(now);

        if (isInactive()) {
            throw new AffiliationAlreadyInactiveException(id.toString());
        }

        if (now.isBefore(startedAt)) {
            throw new AffiliationEndBeforeStartException(startedAt, now);
        }

        status = AffiliationStatus.INACTIVE;
        endedAt = now;
    }

    public void reactivate() {
        if (isActive()) {
            throw new AffiliationAlreadyActiveException(id.toString());
        }

        status = AffiliationStatus.ACTIVE;
        endedAt = null;
    }

    private static void validateState(
            AffiliationStatus status,
            Instant startedAt,
            Instant endedAt
    ) {
        if (status == AffiliationStatus.ACTIVE && endedAt != null) {
            throw new InvalidAffiliationStateException(status, endedAt);
        }

        if (status == AffiliationStatus.INACTIVE && endedAt == null) {
            throw new InvalidAffiliationStateException(status, null);
        }

        if (endedAt != null && endedAt.isBefore(startedAt)) {
            throw new AffiliationEndBeforeStartException(
                    startedAt,
                    endedAt
            );
        }
    }

    private static void validateNow(Instant now) {
        Objects.requireNonNull(now, "now cannot be null");
    }
}