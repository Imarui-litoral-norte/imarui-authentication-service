package br.com.imarui.ima.identity.infra.persistence.entity.identity;

import br.com.imarui.ima.identity.core.domain.enums.identity.IdentityStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Entity
@Table(
        name = "person_identities",
        indexes = {
                @Index(name = "idx_person_identities_cpf", columnList = "cpf")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_person_identities_cpf", columnNames = "cpf")
        }
)
@DiscriminatorValue("PERSON")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PersonIdentityEntity extends IdentityEntity {

    @Column(name = "full_name", nullable = false, length = 150)
    private String fullName;

    @Column(name = "cpf", nullable = false, updatable = false, length = 11)
    private String cpf;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Embedded
    private IdentityPhoneNumberEmbeddable phoneNumber;

    @Embedded
    private IdentityProfilePhotoEmbeddable profilePhoto;

    public PersonIdentityEntity(
            UUID id,
            IdentityEmailEmbeddable primaryEmail,
            String fullName,
            String cpf,
            LocalDate birthDate,
            IdentityPhoneNumberEmbeddable phoneNumber,
            IdentityProfilePhotoEmbeddable profilePhoto,
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
        this.fullName = fullName;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.profilePhoto = profilePhoto;
    }

    public void synchronize(
            IdentityEmailEmbeddable primaryEmail,
            String fullName,
            LocalDate birthDate,
            IdentityPhoneNumberEmbeddable phoneNumber,
            IdentityProfilePhotoEmbeddable profilePhoto,
            IdentityStatus status,
            Instant updatedAt,
            Instant activatedAt,
            Instant disabledAt
    ) {
        synchronizeBaseState(
                primaryEmail,
                status,
                updatedAt,
                activatedAt,
                disabledAt
        );
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.profilePhoto = profilePhoto;
    }
}
