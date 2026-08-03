package br.com.imarui.identity.identity.infra.persistence.entity.identity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IdentityPhoneNumberEmbeddable {

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "phone_verified")
    private Boolean verified;

    @Column(name = "phone_verified_at")
    private Instant verifiedAt;

    @Column(name = "phone_created_at")
    private Instant createdAt;

    @Column(name = "phone_updated_at")
    private Instant updatedAt;

    public IdentityPhoneNumberEmbeddable(
            String phoneNumber,
            boolean verified,
            Instant verifiedAt,
            Instant createdAt,
            Instant updatedAt
    ) {
        this.phoneNumber = phoneNumber;
        this.verified = verified;
        this.verifiedAt = verifiedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
