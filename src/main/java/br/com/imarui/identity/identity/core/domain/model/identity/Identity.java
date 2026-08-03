package br.com.imarui.identity.identity.core.domain.model.identity;

import br.com.imarui.identity.identity.core.domain.enums.user.IdentityKind;
import br.com.imarui.identity.identity.core.domain.enums.user.UserStatus;
import br.com.imarui.identity.identity.core.domain.exceptions.user.UserAlreadyDisabledException;
import br.com.imarui.identity.identity.core.domain.exceptions.user.UserNotDisabledException;
import br.com.imarui.identity.identity.core.domain.exceptions.user.UserNowInstantRequiredException;
import br.com.imarui.identity.identity.core.domain.model.*;
import br.com.imarui.identity.identity.core.domain.model.affiliation.Affiliation;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
public class Identity {

    private final IdentityId id;

    private FullName name;
    private LocalDate birthDate;

    private Set<IdentityEmail> emails;
    private IdentityPhoneNumber phoneNumber;
    private ProfilePhoto profilePhoto;

    private UserStatus status;
    private final IdentityKind kind;

    private Set<Affiliation> affiliations;

    private final Instant createdAt;
    private Instant updatedAt;
    private Instant disabledAt;

    private Identity(
            IdentityId id,
            FullName name,
            LocalDate birthDate,
            Set<IdentityEmail> emails,
            IdentityPhoneNumber phoneNumber,
            ProfilePhoto profilePhoto,
            UserStatus status,
            IdentityKind kind,
            Set<Affiliation> affiliations,
            Instant createdAt,
            Instant updatedAt,
            Instant disabledAt
    ) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.emails = emails;
        this.phoneNumber = phoneNumber;
        this.profilePhoto = profilePhoto;
        this.status = status;
        this.kind = kind;
        this.affiliations = affiliations;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.disabledAt = disabledAt;
    }

    /**
     * Cria uma nova identidade pendente de ativação.
     *
     * @param id           identificador da identidade
     * @param name         nome da pessoa ou serviço
     * @param birthDate    data de nascimento, quando aplicável
     * @param primaryEmail e-mail principal da identidade
     * @param phoneNumber  telefone da identidade, quando aplicável
     * @param kind         natureza da identidade
     * @param now          instante da criação
     * @return nova identidade pendente
     */
    public static Identity create(
            @NotNull IdentityId id,
            @NotNull FullName name,
            LocalDate birthDate,
            @NotNull IdentityEmail primaryEmail,
            IdentityPhoneNumber phoneNumber,
            @NotNull IdentityKind kind,
            @NotNull Instant now
    ) {
        Objects.requireNonNull(id, "id cannot be null");
        Objects.requireNonNull(name, "name cannot be null");
        Objects.requireNonNull(primaryEmail, "primaryEmail cannot be null");
        Objects.requireNonNull(kind, "kind cannot be null");

        validateNow(now);

        Set<IdentityEmail> emails = new HashSet<>();
        emails.add(primaryEmail);

        return new Identity(
                id,
                name,
                birthDate,
                emails,
                phoneNumber,
                null,
                UserStatus.PENDING,
                kind,
                new HashSet<>(),
                now,
                now,
                null
        );
    }

    /**
     * Reconstitui uma identidade previamente persistida.
     *
     * <p>Este método não representa a criação de uma nova identidade e,
     * portanto, preserva os estados e instantes armazenados.</p>
     */
    public static Identity reconstitute(
            @NotNull IdentityId id,
            @NotNull FullName name,
            LocalDate birthDate,
            @NotNull Set<IdentityEmail> emails,
            IdentityPhoneNumber phoneNumber,
            ProfilePhoto profilePhoto,
            @NotNull UserStatus status,
            @NotNull IdentityKind kind,
            @NotNull Set<Affiliation> affiliations,
            @NotNull Instant createdAt,
            @NotNull Instant updatedAt,
            Instant disabledAt
    ) {
        Objects.requireNonNull(id, "id cannot be null");
        Objects.requireNonNull(name, "name cannot be null");
        Objects.requireNonNull(emails, "emails cannot be null");
        Objects.requireNonNull(status, "status cannot be null");
        Objects.requireNonNull(kind, "kind cannot be null");
        Objects.requireNonNull(affiliations, "affiliations cannot be null");
        Objects.requireNonNull(createdAt, "createdAt cannot be null");
        Objects.requireNonNull(updatedAt, "updatedAt cannot be null");

        validateReconstitutedState(
                emails,
                status,
                createdAt,
                updatedAt,
                disabledAt
        );

        return new Identity(
                id,
                name,
                birthDate,
                new HashSet<>(emails),
                phoneNumber,
                profilePhoto,
                status,
                kind,
                new HashSet<>(affiliations),
                createdAt,
                updatedAt,
                disabledAt
        );
    }

    public boolean isPending() {
        return status == UserStatus.PENDING;
    }

    public boolean isActive() {
        return status == UserStatus.ACTIVE;
    }

    public boolean isDisabled() {
        return status == UserStatus.DISABLED;
    }

    public void activate(@NotNull Instant now) {
        validateNow(now);

        if (isActive()) {
            return;
        }

        if (isDisabled()) {
            throw new IllegalStateException(
                    "A disabled user cannot be activated directly."
            );
        }

        status = UserStatus.ACTIVE;
        updatedAt = now;
    }

    public void disable(@NotNull Instant now) {
        validateNow(now);

        if (isDisabled()) {
            throw new UserAlreadyDisabledException(id.toString());
        }

        status = UserStatus.DISABLED;
        disabledAt = now;
        updatedAt = now;
    }

    public void reactivate(@NotNull Instant now) {
        validateNow(now);

        if (!isDisabled()) {
            throw new UserNotDisabledException(id.toString());
        }

        status = UserStatus.ACTIVE;
        disabledAt = null;
        updatedAt = now;
    }

    private static void validateReconstitutedState(
            Set<IdentityEmail> emails,
            UserStatus status,
            Instant createdAt,
            Instant updatedAt,
            Instant disabledAt
    ) {
        if (emails.isEmpty()) {
            throw new IllegalArgumentException(
                    "user must have at least one email"
            );
        }

        if (updatedAt.isBefore(createdAt)) {
            throw new IllegalArgumentException(
                    "updatedAt cannot be before createdAt"
            );
        }

        if (status == UserStatus.DISABLED && disabledAt == null) {
            throw new IllegalArgumentException(
                    "disabled user must have disabledAt"
            );
        }

        if (status != UserStatus.DISABLED && disabledAt != null) {
            throw new IllegalArgumentException(
                    "non-disabled user cannot have disabledAt"
            );
        }

        if (disabledAt != null && disabledAt.isBefore(createdAt)) {
            throw new IllegalArgumentException(
                    "disabledAt cannot be before createdAt"
            );
        }
    }

    private static void validateNow(Instant now) {
        if (now == null) {
            throw new UserNowInstantRequiredException(
                    "now is required."
            );
        }
    }
}