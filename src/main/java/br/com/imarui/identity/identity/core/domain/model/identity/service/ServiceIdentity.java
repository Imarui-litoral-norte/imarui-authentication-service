package br.com.imarui.identity.identity.core.domain.model.identity.service;

import br.com.imarui.identity.identity.core.domain.enums.identity.IdentityKind;
import br.com.imarui.identity.identity.core.domain.enums.identity.IdentityStatus;
import br.com.imarui.identity.identity.core.domain.model.identity.Identity;
import br.com.imarui.identity.identity.core.domain.model.identity.IdentityEmail;
import br.com.imarui.identity.identity.core.domain.model.identity.IdentityId;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.util.Objects;

@Getter
public final class ServiceIdentity extends Identity {

    private ServiceName name;
    private ServiceDescription description;

    private ServiceIdentity(
            IdentityId id,
            IdentityEmail primaryEmail,
            ServiceName name,
            ServiceDescription description,
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

        this.name = Objects.requireNonNull(
                name,
                "name cannot be null"
        );

        this.description = description;
    }

    public static ServiceIdentity create(
            @NotNull IdentityId id,
            @NotNull IdentityEmail primaryEmail,
            @NotNull ServiceName name,
            @Nullable ServiceDescription description,
            @NotNull Instant now
    ) {
        Objects.requireNonNull(now, "now cannot be null");

        return new ServiceIdentity(
                id,
                primaryEmail,
                name,
                description,
                IdentityStatus.PENDING,
                now,
                now,
                null,
                null
        );
    }

    public static ServiceIdentity reconstitute(
            @NotNull IdentityId id,
            @NotNull IdentityEmail primaryEmail,
            @NotNull ServiceName name,
            @Nullable ServiceDescription description,
            @NotNull IdentityStatus status,
            @NotNull Instant createdAt,
            @NotNull Instant updatedAt,
            @Nullable Instant activatedAt,
            @Nullable Instant disabledAt
    ) {
        return new ServiceIdentity(
                id,
                primaryEmail,
                name,
                description,
                status,
                createdAt,
                updatedAt,
                activatedAt,
                disabledAt
        );
    }

    @Override
    public @NotNull IdentityKind getKind() {
        return IdentityKind.SERVICE;
    }

    public void rename(
            @NotNull ServiceName newName,
            @NotNull Instant now
    ) {
        Objects.requireNonNull(
                newName,
                "newName cannot be null"
        );

        if (name.equals(newName)) {
            return;
        }

        registerChange(now);
        name = newName;
    }

    public void changeDescription(
            @NotNull ServiceDescription newDescription,
            @NotNull Instant now
    ) {
        Objects.requireNonNull(
                newDescription,
                "newDescription cannot be null"
        );

        if (newDescription.equals(description)) {
            return;
        }

        registerChange(now);
        description = newDescription;
    }

    public void removeDescription(@NotNull Instant now) {
        if (description == null) {
            return;
        }

        registerChange(now);
        description = null;
    }
}
