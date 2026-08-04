package br.com.imarui.ima.platform.security.principal;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private final Long userId;
    private final Long sessionId;
    private final Instant accessTokenExpiresAt;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(
            Long userId,
            Long sessionId,
            Instant accessTokenExpiresAt,
            Collection<? extends GrantedAuthority> authorities
    ) {
        this.userId = userId;
        this.sessionId = sessionId;
        this.accessTokenExpiresAt = accessTokenExpiresAt;
        this.authorities = authorities == null ? List.of() : List.copyOf(authorities);
    }

    public Long getUserId() {
        return userId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Instant getAccessTokenExpiresAt() {
        return accessTokenExpiresAt;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return String.valueOf(userId);
    }
}
