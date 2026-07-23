package br.com.imarui.authentication.api.module.user;

import java.util.List;

public interface UserQuery {

    boolean existsById(Long userId);

    List<Long> findActiveUserIds();

    boolean existsActiveUserById(Long userId);
}