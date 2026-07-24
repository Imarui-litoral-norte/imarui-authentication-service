package br.com.imarui.identity.authentication.core.port;

import br.com.imarui.identity.authentication.core.domain.model.IssuedAccessToken;
import br.com.imarui.identity.authentication.core.domain.model.Session;
import br.com.imarui.identity.identity.core.domain.model.User;

public interface JwtService {
    IssuedAccessToken generateAccessToken(User user, Session session);
}
