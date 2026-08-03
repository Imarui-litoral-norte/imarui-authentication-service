package br.com.imarui.identity.identity.core.repository;

import br.com.imarui.identity.identity.core.domain.model.identity.Identity;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Identity create(Identity user);

    Identity save(Identity user);

    List<Identity> findAll();

    Optional<Identity> findById(Long id);

    Optional<Identity> findByIdForUpdate(Long id);

    Optional<Identity> findByCpf(String cpf);

    Optional<Identity> findByCpfForUpdate(String cpf);

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

    boolean existsByRg(String rg);

    boolean existsById(Long id);

    List<Long> findActiveUserIds();

    boolean existsActiveById(Long id);
}
