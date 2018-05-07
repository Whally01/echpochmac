package ru.itis.echpochmac.service;

import ru.itis.echpochmac.model.Role;
import ru.itis.echpochmac.model.RoleName;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> getRole(int id);

    Optional<Role> findByName(RoleName roleName);
}
