package br.com.imarui.identity.authentication.core.application.service.internal;

import br.com.imarui.identity.authentication.core.application.result.IssuedPasswordResetToken;
import br.com.imarui.identity.authentication.core.port.ApplicationUrlProperties;
import br.com.imarui.identity.authentication.core.port.RefreshTokenGenerator;
import br.com.imarui.identity.authentication.core.port.RefreshTokenHashService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordResetTokenIssuer {
    private final RefreshTokenGenerator tokenGenerator;
    private final RefreshTokenHashService tokenHashService;
    private final ApplicationUrlProperties urlProperties;

    public IssuedPasswordResetToken issue() {
        String rawToken = tokenGenerator.generate();
        return new IssuedPasswordResetToken(
                rawToken,
                tokenHashService.hash(rawToken),
                urlProperties.baseUrl() + "/password-recovery/reset?token=" + rawToken
        );
    }
}
