package br.com.imarui.ima.authentication.infra.persistence.jpa;

import br.com.imarui.ima.authentication.infra.persistence.entity.SessionEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SessionJpaRepository
        extends JpaRepository<SessionEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            SELECT session
            FROM SessionEntity session
            WHERE session.id = :id
            """)
    Optional<SessionEntity> findByIdForUpdate(
            @Param("id") Long id
    );

}
