package br.com.imarui.ima.shared.bootstrap;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.LocalDate;

@ConfigurationProperties(prefix = "development.admin")
public record AdminBootstrapConfig(
        String name,
        LocalDate birthDate,
        String email,
        String cpf,
        String rg,
        String password,
        String phoneNumber
) {
}
