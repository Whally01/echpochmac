package ru.itis.echpochmac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.echpochmac.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhone(String phone_number);

    Optional<User> findByLoginOrPhone(String login, String phone_number);

    List<User> findByIdIn(List<Long> userIds);

    Optional<User> findById(Long userId);

    Optional<User> findByLogin(String login);

    Boolean existsByLogin(String login);

    Boolean existsByPhone(String phone_number);
}
