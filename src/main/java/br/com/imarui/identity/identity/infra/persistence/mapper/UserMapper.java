package br.com.imarui.identity.identity.infra.persistence.mapper;

import br.com.imarui.identity.identity.core.domain.model.identity.Identity;
import br.com.imarui.identity.identity.infra.persistence.entity.UserEntity;

public final class UserMapper {

    private UserMapper() {
    }

    public static Identity toDomain(UserEntity entity) {
        if (entity == null) {
            return null;
        }

        return Identity.reconstitute(
                entity.getId(),
                entity.getName(),
                entity.getBirthDate(),
                entity.getEmail(),
                entity.getCpf(),
                entity.getRg(),
                entity.getPasswordHash(),
                entity.getPhoneNumber(),
                entity.getProfilePhotoUrl(),
                entity.getAccountStatus(),
                entity.getAuthenticationStatus(),
                entity.getCredentialStatus(),
                entity.getLastLoginAt(),
                entity.getCreatedAt(),
                entity.getDisabledAt(),
                entity.getUpdatedAt(),
                entity.getPasswordChangedAt(),
                entity.getFailedLoginAttempts()
        );
    }

    public static UserEntity toEntity(Identity domain) {
        if (domain == null) {
            return null;
        }

        UserEntity entity = new UserEntity();

        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setBirthDate(domain.getBirthDate());
        entity.setEmail(domain.getEmail());
        entity.setCpf(domain.getCpf());
        entity.setRg(domain.getRg());
        entity.setPasswordHash(domain.getPasswordHash());
        entity.setPhoneNumber(domain.getPhoneNumber());
        entity.setProfilePhotoUrl(domain.getProfilePhotoUrl());
        entity.setAccountStatus(domain.getAccountStatus());
        entity.setAuthenticationStatus(domain.getAuthenticationStatus());
        entity.setCredentialStatus(domain.getCredentialStatus());
        entity.setLastLoginAt(domain.getLastLoginAt());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setDisabledAt(domain.getDisabledAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        entity.setPasswordChangedAt(
                domain.getPasswordChangedAt()
        );
        entity.setFailedLoginAttempts(
                domain.getFailedLoginAttempts()
        );

        return entity;
    }
}
