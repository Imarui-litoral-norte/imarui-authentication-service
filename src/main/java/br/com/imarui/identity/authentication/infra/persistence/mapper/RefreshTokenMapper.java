package br.com.imarui.identity.authentication.infra.persistence.mapper;

import br.com.imarui.identity.authentication.core.domain.model.RefreshToken;
import br.com.imarui.identity.authentication.core.domain.model.Session;
import br.com.imarui.identity.authentication.infra.persistence.entity.RefreshTokenEntity;

public final class RefreshTokenMapper {

    private RefreshTokenMapper() {
    }

    public static RefreshToken toDomain(
            RefreshTokenEntity entity,
            Session session
    ) {
        if (entity == null) {
            return null;
        }

        return RefreshToken.reconstitute(
                entity.getId(),
                session,
                entity.getTokenHash(),
                entity.getCreatedAt(),
                entity.getExpiresAt(),
                entity.getStatus(),
                entity.getReplacedByTokenId()
        );
    }

    public static RefreshTokenEntity toEntity(RefreshToken token) {
        if (token == null) {
            return null;
        }

        RefreshTokenEntity entity = new RefreshTokenEntity();

        entity.setId(token.getId());
        entity.setSessionId(token.getSession().getId());
        entity.setTokenHash(token.getTokenHash());
        entity.setCreatedAt(token.getCreatedAt());
        entity.setExpiresAt(token.getExpiresAt());
        entity.setStatus(token.getStatus());
        entity.setReplacedByTokenId(token.getReplacedByTokenId());

        return entity;
    }
}