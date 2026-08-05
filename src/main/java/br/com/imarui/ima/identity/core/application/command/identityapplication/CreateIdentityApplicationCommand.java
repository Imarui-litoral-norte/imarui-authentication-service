package br.com.imarui.ima.identity.core.application.command.identityapplication;

public record CreateIdentityApplicationCommand(
        String fullName,
        String cpf,
        String email,
        String phoneNumber
) {
}
