package br.com.imarui.identity.identity.infra.persistence.adapter;

import br.com.imarui.identity.identity.core.application.exceptions.user.RegistrationConflictException;
import br.com.imarui.identity.identity.core.domain.enums.AccountStatus;
import br.com.imarui.identity.identity.core.domain.enums.AuthenticationStatus;
import br.com.imarui.identity.identity.core.domain.enums.CredentialStatus;
import br.com.imarui.identity.identity.core.domain.model.identity.Identity;
import br.com.imarui.identity.identity.core.repository.UserRepository;
import br.com.imarui.identity.identity.infra.persistence.jpa.UserJpaRepository;
import br.com.imarui.identity.identity.infra.persistence.entity.UserEntity;
import br.com.imarui.identity.identity.infra.persistence.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter
        implements UserRepository {

    private final UserJpaRepository jpaRepository;

    @Override
    public Identity create(Identity user) {
        try {
            UserEntity entity =
                    UserMapper.toEntity(user);

            UserEntity savedEntity =
                    jpaRepository.saveAndFlush(entity);

            return UserMapper.toDomain(savedEntity);
        } catch (DataIntegrityViolationException exception) {
            if (isRegistrationUniqueConflict(exception)) {
                throw new RegistrationConflictException();
            }
            throw exception;
        }
    }

    @Override
    public Identity save(Identity user) {
        UserEntity entity =
                UserMapper.toEntity(user);

        UserEntity savedEntity =
                jpaRepository.save(entity);

        return UserMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Identity> findById(Long id) {
        return jpaRepository.findById(id)
                .map(UserMapper::toDomain);
    }

    @Override
    public Optional<Identity> findByIdForUpdate(Long id) {
        return jpaRepository.findByIdForUpdate(id)
                .map(UserMapper::toDomain);
    }

    @Override
    public Optional<Identity> findByCpf(String cpf) {
        return jpaRepository.findByCpf(cpf)
                .map(UserMapper::toDomain);
    }

    @Override
    public Optional<Identity> findByCpfForUpdate(String cpf) {
        return jpaRepository.findByCpfForUpdate(cpf)
                .map(UserMapper::toDomain);
    }

    @Override
    public boolean existsByCpf(String cpf) {
        return jpaRepository.existsByCpf(cpf);
    }

    @Override
    public boolean existsByEmail(String email) {
        return email != null && jpaRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByRg(String rg) {
        return rg != null && jpaRepository.existsByRg(rg);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public List<Long> findActiveUserIds() {
        return jpaRepository.findActiveUserIds();
    }

    @Override
    public boolean existsActiveById(Long id) {
        return jpaRepository.existsByIdAndAccountStatusAndAuthenticationStatusAndCredentialStatus(
                id,
                AccountStatus.ACTIVE,
                AuthenticationStatus.ENABLED,
                CredentialStatus.PERMANENT
        );
    }

    @Override
    public List<Identity> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(UserMapper::toDomain)
                .toList();
    }

    private boolean isRegistrationUniqueConflict(DataIntegrityViolationException exception) {
        String message = exception.getMostSpecificCause().getMessage();
        if (message == null) {
            return false;
        }
        String normalized = message.toLowerCase();
        return normalized.contains("authentication_users")
                && (normalized.contains("cpf")
                || normalized.contains("email")
                || normalized.contains("rg"));
    }
}
