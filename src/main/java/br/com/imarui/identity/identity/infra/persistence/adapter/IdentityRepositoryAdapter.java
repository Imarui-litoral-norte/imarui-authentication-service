package br.com.imarui.identity.identity.infra.persistence.adapter;

import br.com.imarui.identity.identity.core.domain.model.identity.*;
import br.com.imarui.identity.identity.core.domain.model.identity.LegalEntity.Cnpj;
import br.com.imarui.identity.identity.core.domain.model.identity.person.Cpf;
import br.com.imarui.identity.identity.infra.persistence.entity.identity.IdentityEntity;
import br.com.imarui.identity.identity.infra.persistence.jpa.IdentityJpaRepository;
import br.com.imarui.identity.identity.infra.persistence.mapper.IdentityPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class IdentityRepositoryAdapter {

    private final IdentityJpaRepository jpaRepository;
    private final IdentityPersistenceMapper mapper;

    public Identity save(Identity identity) {
        IdentityEntity entity = jpaRepository
                .findById(identity.getId().value())
                .map(existing -> {
                    mapper.updateEntity(identity, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toEntity(identity));

        return mapper.toDomain(
                jpaRepository.save(entity)
        );
    }

    public Optional<Identity> findById(IdentityId identityId) {
        return jpaRepository
                .findById(identityId.value())
                .map(mapper::toDomain);
    }

    public Optional<Identity> findByIdForUpdate(
            IdentityId identityId
    ) {
        return jpaRepository
                .findByIdForUpdate(identityId.value())
                .map(mapper::toDomain);
    }

    public Optional<Identity> findPersonByCpf(Cpf cpf) {
        return jpaRepository
                .findPersonByCpf(cpf.value())
                .map(mapper::toDomain);
    }

    public Optional<Identity> findLegalEntityByCnpj(Cnpj cnpj) {
        return jpaRepository
                .findLegalEntityByCnpj(cnpj.value())
                .map(mapper::toDomain);
    }

    public boolean existsByPrimaryEmail(Email email) {
        return jpaRepository.existsByPrimaryEmail(
                email.value()
        );
    }
}
