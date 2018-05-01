package ru.itis.echpochmac.service;

import ru.itis.echpochmac.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    Optional<User> findByPhone(String phone_number);

    Optional<User> findByLoginOrPhone(String login, String phone_number);

    List<User> findByIdIn(List<Long> userIds);

    Optional<User> findById(Long userId);

    Optional<User> findByLogin(String login);

    Boolean existsByLogin(String login);

    Boolean existsByPhone(String phone);

    void delete(long id);

    User save(User user);

}
