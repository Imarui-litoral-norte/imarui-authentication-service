package br.com.imarui.identity.authentication.core.port;

import br.com.imarui.identity.authentication.core.domain.model.IssuedAccessToken;
import br.com.imarui.identity.authentication.core.domain.model.Session;
import br.com.imarui.identity.identity.core.domain.model.identity.Identity;

public interface JwtService {
    IssuedAccessToken generateAccessToken(Identity user, Session session);
}
