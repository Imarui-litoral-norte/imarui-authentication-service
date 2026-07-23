package br.com.imarui.memberships.core.port;

public interface CreateUserWithPendingFirstAccessPort {

    Long createPendingUser(
            String name,
            String email,
            String cpf,
            String phoneNumber
    );
}
