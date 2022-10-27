package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepo;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepo = roleRepository;
    }

    @Override
    public Role findRoleByName(String name) {
        return roleRepo.findRoleByName(name);
    }

    @Override
    public void addRole(Role role) {

    }
}
