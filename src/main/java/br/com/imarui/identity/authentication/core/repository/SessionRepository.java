package br.com.imarui.identity.authentication.core.repository;

import br.com.imarui.identity.authentication.core.domain.model.Session;

import java.util.List;
import java.util.Optional;

public interface SessionRepository {

    Session save(Session session);

    List<Session> findAll();

    Optional<Session> findById(Long id);

    Optional<Session> findByIdForUpdate(Long id);

    Optional<Long> findUserIdById(Long id);

    List<Session> findByUserId(Long userId);

    Optional<Session> findActiveByUserId(Long userId);

    Optional<Session> findActiveByUserIdForUpdate(Long userId);

    void revokeActiveByUserId(Long userId);
}
