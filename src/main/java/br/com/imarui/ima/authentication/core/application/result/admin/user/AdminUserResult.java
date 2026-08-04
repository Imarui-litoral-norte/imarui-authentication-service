package br.com.imarui.ima.authentication.core.application.result.admin.user;

import br.com.imarui.ima.identity.core.domain.model.identity.Identity;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

public record AdminUserResult(
        Long id,
        String name,
        String cpf,
        String email,
        String phone,
        String status,
        Boolean passwordChangeRequired,
        Instant createdAt,
        Instant updatedAt
) {

    public static AdminUserResult from(Identity user) {
        Objects.requireNonNull(user, "user cannot be null");

        return new AdminUserResult(
                user.getId(),
                user.getName(),
                user.getCpf(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getStatus().name(),
                user.isChangePasswordRequired(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public static List<AdminUserResult> from(List<Identity> users) {
        Objects.requireNonNull(users, "users cannot be null");

        return users.stream()
                .map(AdminUserResult::from)
                .toList();
    }
}