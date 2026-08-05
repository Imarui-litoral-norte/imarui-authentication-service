package br.com.imarui.ima.identity.infra.persistence.jpa;

import br.com.imarui.ima.identity.core.domain.enums.identity.IdentityStatus;
import br.com.imarui.ima.identity.infra.persistence.entity.identity.*;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IdentityJpaRepository
        extends JpaRepository<IdentityEntity, UUID> {

    List<IdentityEntity> findAllByStatusOrderByCreatedAtDesc(
            IdentityStatus status
    );

    Optional<IdentityEntity> findByIdAndStatus(
            UUID identityId,
            IdentityStatus status
    );

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            select identity
              from IdentityEntity identity
             where identity.id = :identityId
            """)
    Optional<IdentityEntity> findByIdForUpdate(
            @Param("identityId") UUID identityId
    );

    @Query("""
            select person
              from PersonIdentityEntity person
             where person.cpf = :cpf
               and person.status = :status
            """)
    Optional<PersonIdentityEntity> findPersonByCpf(
            @Param("cpf") String cpf,
            @Param("status") IdentityStatus status
    );

    @Query("""
            select legalEntity
              from LegalEntityIdentityEntity legalEntity
             where legalEntity.cnpj = :cnpj
               and legalEntity.status = :status
            """)
    Optional<LegalEntityIdentityEntity> findLegalEntityByCnpj(
            @Param("cnpj") String cnpj,
            @Param("status") IdentityStatus status
    );

    @Query("""
            select (count(identity) > 0)
              from IdentityEntity identity
             where lower(identity.primaryEmail.email) = lower(:email)
            """)
    boolean existsByPrimaryEmail(
            @Param("email") String email
    );
}
