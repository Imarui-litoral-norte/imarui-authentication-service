package br.com.imarui.identity.identity.core.repository;

import br.com.imarui.identity.identity.core.domain.model.identity.Email;
import br.com.imarui.identity.identity.core.domain.model.identity.Identity;
import br.com.imarui.identity.identity.core.domain.model.identity.IdentityId;
import br.com.imarui.identity.identity.core.domain.model.identity.LegalEntity.Cnpj;
import br.com.imarui.identity.identity.core.domain.model.identity.person.Cpf;

import java.util.Optional;

public interface IdentityRepository {

    Identity save(Identity identity);

    Optional<Identity> findById(IdentityId identityId);

    Optional<Identity> findByIdForUpdate(IdentityId identityId);

    Optional<Identity> findPersonByCpf(Cpf cpf);

    Optional<Identity> findLegalEntityByCnpj(Cnpj cnpj);

    boolean existsByPrimaryEmail(Email email);
}
