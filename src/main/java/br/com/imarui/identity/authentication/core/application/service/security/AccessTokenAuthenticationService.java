package br.com.imarui.identity.authentication.core.application.service.security;

import br.com.imarui.identity.authentication.core.application.exceptions.session.SessionInvalidException;
import br.com.imarui.identity.authentication.core.application.exceptions.session.SessionNotFoundException;
import br.com.imarui.identity.authentication.core.application.exceptions.session.SessionUserMismatchException;
import br.com.imarui.identity.identity.core.application.exceptions.user.UserDisabledException;
import br.com.imarui.identity.identity.core.application.exceptions.user.UserIdNotFoundException;
import br.com.imarui.identity.authentication.core.domain.model.Session;
import br.com.imarui.identity.identity.core.domain.model.User;
import br.com.imarui.identity.authentication.core.repository.SessionRepository;
import br.com.imarui.identity.authentication.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AccessTokenAuthenticationService {

    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final Clock clock;

    public void validate(Long userId, Long sessionId) {
        Instant now = Instant.now(clock);

        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new SessionNotFoundException("Session not found."));

        if (!session.getUserId().equals(userId)) {
            throw new SessionUserMismatchException("Session does not belong to this user.");
        }

        if (!session.isValid(now)) {
            throw new SessionInvalidException("Session invalid.");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserIdNotFoundException("User not found with this cpf."));

        if (!user.isActive()) {
            throw new UserDisabledException("User inactive.");
        }
    }
}
