package ru.itis.echpochmac.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.echpochmac.model.Role;
import ru.itis.echpochmac.model.RoleName;
import ru.itis.echpochmac.repository.RoleRepository;
import ru.itis.echpochmac.service.IRoleService;

import java.util.Optional;

@Service
public class RoleService implements IRoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<Role> findByName(RoleName roleName) {
        return roleRepository.findByName(roleName);
    }
}
