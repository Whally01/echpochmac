package ru.itis.echpochmac.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.echpochmac.model.Role;
import ru.itis.echpochmac.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhone(String phone_number);

    Optional<User> findByLoginOrPhone(String login, String phone_number);

    Page<User> findUsersByRoles(Role role, Pageable pageable);

    List<User> findByIdIn(List<Long> userIds);

    @NotNull
    Optional<User> findById(@NotNull Long userId);

    Optional<User> findByLogin(String login);

    Boolean existsByLogin(String login);

    Boolean existsByPhone(String phone_number);
}
