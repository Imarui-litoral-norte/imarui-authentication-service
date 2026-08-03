package br.com.imarui.identity.identity.core.domain.model.identity;

import java.time.Instant;
import java.util.Objects;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class IdentityPhoneNumber {

    private PhoneNumber phoneNumber;
    private boolean verified;
    private Instant verifiedAt;
    private final Instant createdAt;
    private Instant updatedAt;

    private IdentityPhoneNumber(
            PhoneNumber phoneNumber,
            boolean verified,
            Instant verifiedAt,
            Instant createdAt,
            Instant updatedAt
    ) {
        validate(
                phoneNumber,
                verified,
                verifiedAt,
                createdAt,
                updatedAt
        );

        this.phoneNumber = phoneNumber;
        this.verified = verified;
        this.verifiedAt = verifiedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static @NotNull IdentityPhoneNumber create(
            @NotNull PhoneNumber phoneNumber,
            @NotNull Instant createdAt
    ) {
        return new IdentityPhoneNumber(
                phoneNumber,
                false,
                null,
                createdAt,
                createdAt
        );
    }

    public static @NotNull IdentityPhoneNumber reconstitute(
            @NotNull PhoneNumber phoneNumber,
            boolean verified,
            @Nullable Instant verifiedAt,
            @NotNull Instant createdAt,
            @NotNull Instant updatedAt
    ) {
        return new IdentityPhoneNumber(
                phoneNumber,
                verified,
                verifiedAt,
                createdAt,
                updatedAt
        );
    }

    private static void validate(
            PhoneNumber phoneNumber,
            boolean verified,
            Instant verifiedAt,
            Instant createdAt,
            Instant updatedAt
    ) {
        Objects.requireNonNull(
                phoneNumber,
                "PhoneNumber must not be null."
        );

        Objects.requireNonNull(
                createdAt,
                "CreatedAt must not be null."
        );

        Objects.requireNonNull(
                updatedAt,
                "UpdatedAt must not be null."
        );

        if (verified && verifiedAt == null) {
            throw new IllegalArgumentException(
                    "Verified phone number must have a verification timestamp."
            );
        }

        if (!verified && verifiedAt != null) {
            throw new IllegalArgumentException(
                    "Unverified phone number must not have a verification timestamp."
            );
        }

        if (verifiedAt != null && verifiedAt.isBefore(createdAt)) {
            throw new IllegalArgumentException(
                    "Verification timestamp must not be before creation timestamp."
            );
        }

        if (updatedAt.isBefore(createdAt)) {
            throw new IllegalArgumentException(
                    "UpdatedAt must not be before CreatedAt."
            );
        }
    }

    public @NotNull PhoneNumber phoneNumber() {
        return phoneNumber;
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

    public @NotNull Instant updatedAt() {
        return updatedAt;
    }
}