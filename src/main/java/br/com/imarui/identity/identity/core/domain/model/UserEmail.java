package br.com.imarui.identity.identity.core.domain.model;

import br.com.imarui.identity.identity.core.domain.enums.user.EmailType;
import br.com.imarui.identity.identity.core.domain.model.id.UserEmailId;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.util.Objects;

public class UserEmail {

    private final UserEmailId id;
    private final Email email;
    private EmailType type;
    private boolean verified;
    private Instant verifiedAt;
    private final Instant createdAt;

    private UserEmail(
            UserEmailId id,
            Email email,
            EmailType type,
            boolean verified,
            Instant verifiedAt,
            Instant createdAt
    ) {
        validate(
                id,
                email,
                type,
                verified,
                verifiedAt,
                createdAt
        );

        this.id = id;
        this.email = email;
        this.type = type;
        this.verified = verified;
        this.verifiedAt = verifiedAt;
        this.createdAt = createdAt;
    }

    public static @NotNull UserEmail create(
            @NotNull Email email,
            @NotNull EmailType type,
            @NotNull Instant createdAt
    ) {
        return new UserEmail(
                UserEmailId.generate(),
                email,
                type,
                false,
                null,
                createdAt
        );
    }

    public static @NotNull UserEmail reconstitute(
            @NotNull UserEmailId id,
            @NotNull Email email,
            @NotNull EmailType type,
            boolean verified,
            @Nullable Instant verifiedAt,
            @NotNull Instant createdAt
    ) {
        return new UserEmail(
                id,
                email,
                type,
                verified,
                verifiedAt,
                createdAt
        );
    }

    private static void validate(
            UserEmailId id,
            Email email,
            EmailType type,
            boolean verified,
            Instant verifiedAt,
            Instant createdAt
    ) {
        Objects.requireNonNull(
                id,
                "UserEmailId must not be null."
        );

        Objects.requireNonNull(
                email,
                "Email must not be null."
        );

        Objects.requireNonNull(
                type,
                "EmailType must not be null."
        );

        Objects.requireNonNull(
                createdAt,
                "CreatedAt must not be null."
        );

        if (verified && verifiedAt == null) {
            throw new IllegalArgumentException(
                    "Verified email must have a verification timestamp."
            );
        }

        if (!verified && verifiedAt != null) {
            throw new IllegalArgumentException(
                    "Unverified email must not have a verification timestamp."
            );
        }

        if (verifiedAt != null && verifiedAt.isBefore(createdAt)) {
            throw new IllegalArgumentException(
                    "Verification timestamp must not be before creation timestamp."
            );
        }
    }

    public @NotNull UserEmailId id() {
        return id;
    }

    public @NotNull Email email() {
        return email;
    }

    public @NotNull EmailType type() {
        return type;
    }

    public boolean isVerified() {
        return verified;
    }

    public @Nullable Instant verifiedAt() {
        return verifiedAt;
    }

    public @NotNull Instant createdAt() {
        return createdAt;
    }
}