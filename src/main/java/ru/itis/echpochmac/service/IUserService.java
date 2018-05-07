package ru.itis.echpochmac.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.itis.echpochmac.model.Role;
import ru.itis.echpochmac.model.RoleName;
import ru.itis.echpochmac.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    Page<User> findAll(int page);

    Optional<User> findByPhone(String phone_number);

    Optional<User> findByLoginOrPhone(String login, String phone_number);

    Page<User> findUsersByRoles(Role role, Pageable pageable);

    List<User> findByIdIn(List<Long> userIds);

    Optional<User> findById(Long userId);

    Optional<User> findByLogin(String login);

    Boolean existsByLogin(String login);

    Boolean existsByPhone(String phone);

    void delete(long id);

    User save(User user);

    User update(User user);

}
