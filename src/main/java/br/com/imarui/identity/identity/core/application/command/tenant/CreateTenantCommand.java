package br.com.imarui.identity.identity.core.application.command.tenant;

public record CreateTenantCommand(
        String code,
        String name
) {
}