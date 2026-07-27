package br.com.imarui.identity.identity.core.domain.model;

import br.com.imarui.identity.identity.core.domain.model.id.ProfilePhotoId;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.Objects;

public class ProfilePhoto {

    private final ProfilePhotoId id;
    private final String storageKey;
    private final String contentType;
    private final long sizeInBytes;
    private final Instant createdAt;

    private ProfilePhoto(
            ProfilePhotoId id,
            String storageKey,
            String contentType,
            long sizeInBytes,
            Instant createdAt
    ) {
        validate(
                id,
                storageKey,
                contentType,
                sizeInBytes,
                createdAt
        );

        this.id = id;
        this.storageKey = storageKey;
        this.contentType = contentType;
        this.sizeInBytes = sizeInBytes;
        this.createdAt = createdAt;
    }

    public static @NotNull ProfilePhoto create(
            @NotNull String storageKey,
            @NotNull String contentType,
            long sizeInBytes,
            @NotNull Instant createdAt
    ) {
        return new ProfilePhoto(
                ProfilePhotoId.generate(),
                storageKey,
                contentType,
                sizeInBytes,
                createdAt
        );
    }

    public static @NotNull ProfilePhoto reconstitute(
            @NotNull ProfilePhotoId id,
            @NotNull String storageKey,
            @NotNull String contentType,
            long sizeInBytes,
            @NotNull Instant createdAt
    ) {
        return new ProfilePhoto(
                id,
                storageKey,
                contentType,
                sizeInBytes,
                createdAt
        );
    }

    private static void validate(
            ProfilePhotoId id,
            String storageKey,
            String contentType,
            long sizeInBytes,
            Instant createdAt
    ) {
        Objects.requireNonNull(
                id,
                "ProfilePhotoId must not be null."
        );

        Objects.requireNonNull(
                storageKey,
                "StorageKey must not be null."
        );

        Objects.requireNonNull(
                contentType,
                "ContentType must not be null."
        );

        Objects.requireNonNull(
                createdAt,
                "CreatedAt must not be null."
        );

        if (storageKey.isBlank()) {
            throw new IllegalArgumentException(
                    "StorageKey must not be blank."
            );
        }

        if (contentType.isBlank()) {
            throw new IllegalArgumentException(
                    "ContentType must not be blank."
            );
        }

        if (sizeInBytes <= 0) {
            throw new IllegalArgumentException(
                    "SizeInBytes must be greater than zero."
            );
        }
    }

    public @NotNull ProfilePhotoId id() {
        return id;
    }

    public @NotNull String storageKey() {
        return storageKey;
    }

    public @NotNull String contentType() {
        return contentType;
    }

    public long sizeInBytes() {
        return sizeInBytes;
    }

    public @NotNull Instant createdAt() {
        return createdAt;
    }
}