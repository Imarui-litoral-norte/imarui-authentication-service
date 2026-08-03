package br.com.imarui.identity.identity.infra.persistence.entity.identity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IdentityProfilePhotoEmbeddable {

    @Column(name = "profile_photo_id")
    private UUID id;

    @Column(name = "profile_photo_storage_key", length = 500)
    private String storageKey;

    @Column(name = "profile_photo_content_type", length = 100)
    private String contentType;

    @Column(name = "profile_photo_size_in_bytes")
    private Long sizeInBytes;

    @Column(name = "profile_photo_created_at")
    private Instant createdAt;

    public IdentityProfilePhotoEmbeddable(
            UUID id,
            String storageKey,
            String contentType,
            long sizeInBytes,
            Instant createdAt
    ) {
        this.id = id;
        this.storageKey = storageKey;
        this.contentType = contentType;
        this.sizeInBytes = sizeInBytes;
        this.createdAt = createdAt;
    }
}
