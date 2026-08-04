package br.com.imarui.ima.identity.infra.persistence.jpa;

import br.com.imarui.ima.identity.infra.persistence.entity.identity.*;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface IdentityJpaRepository
        extends JpaRepository<IdentityEntity, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            select identity
              from IdentityJpaEntity identity
             where identity.id = :identityId
            """)
    Optional<IdentityEntity> findByIdForUpdate(
            @Param("identityId") UUID identityId
    );

    @Query("""
            select person
              from PersonIdentityJpaEntity person
             where person.cpf = :cpf
            """)
    Optional<PersonIdentityEntity> findPersonByCpf(
            @Param("cpf") String cpf
    );

    @Query("""
            select legalEntity
              from LegalEntityIdentityJpaEntity legalEntity
             where legalEntity.cnpj = :cnpj
            """)
    Optional<LegalEntityIdentityEntity> findLegalEntityByCnpj(
            @Param("cnpj") String cnpj
    );

    @Query("""
            select (count(identity) > 0)
              from IdentityJpaEntity identity
             where lower(identity.primaryEmail.email) = lower(:email)
            """)
    boolean existsByPrimaryEmail(
            @Param("email") String email
    );
}
