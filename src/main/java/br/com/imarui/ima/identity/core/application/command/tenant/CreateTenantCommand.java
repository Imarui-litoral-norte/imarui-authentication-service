package br.com.imarui.ima.identity.core.application.command.tenant;

public record CreateTenantCommand(
        String code,
        String name
) {
}