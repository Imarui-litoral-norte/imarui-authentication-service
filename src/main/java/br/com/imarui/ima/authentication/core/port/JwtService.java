package br.com.imarui.ima.authentication.core.port;

import br.com.imarui.ima.authentication.core.domain.model.IssuedAccessToken;
import br.com.imarui.ima.authentication.core.domain.model.Session;
import br.com.imarui.ima.identity.core.domain.model.identity.Identity;

public interface JwtService {
    IssuedAccessToken generateAccessToken(Identity user, Session session);
}
