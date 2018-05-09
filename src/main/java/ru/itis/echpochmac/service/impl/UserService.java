package ru.itis.echpochmac.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.itis.echpochmac.model.Role;
import ru.itis.echpochmac.model.RoleName;
import ru.itis.echpochmac.model.User;
import ru.itis.echpochmac.repository.RoleRepository;
import ru.itis.echpochmac.repository.UserRepository;
import ru.itis.echpochmac.service.IUserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public Page<User> findAll(int page) {
        return userRepository.findAll(PageRequest.of(page, 20));
    }

    @Override
    public Optional<User> findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    @Override
    public Optional<User> findByLoginOrPhone(String login, String phone) {
        return userRepository.findByLoginOrPhone(login, phone);
    }

    @Override
    public Page<User> findUsersByRoles(Role role, Pageable pageable) {
        return userRepository.findUsersByRoles(role, pageable);
    }

    @Override
    public List<User> findByIdIn(List<Long> userIds) {
        return userRepository.findByIdIn(userIds);
    }

    @Override
    public Optional<User> findById(String  userId) {
        return userRepository.findById(Long.parseLong(userId));
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public Boolean existsByLogin(String login) {
        return userRepository.existsByLogin(login);
    }

    @Override
    public Boolean existsByPhone(String phone) {
        return userRepository.existsByPhone(phone);
    }

}
