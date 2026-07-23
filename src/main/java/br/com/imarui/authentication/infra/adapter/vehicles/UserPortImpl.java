package br.com.imarui.authentication.infra.adapter.vehicles;

import br.com.imarui.authentication.infra.persistence.jpa.UserJpaRepository;
import br.com.imarui.vehicles.core.port.UserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPortImpl implements UserPort {

    private final UserJpaRepository userJpaRepository;

    @Override
    public boolean existsById(Long userId) {
        return userJpaRepository.existsById(userId);
    }
}
