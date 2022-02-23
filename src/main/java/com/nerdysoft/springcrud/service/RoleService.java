package com.nerdysoft.springcrud.service;

import com.nerdysoft.springcrud.entity.Role;
import com.nerdysoft.springcrud.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleByCode(String code) {
        return roleRepository.findByCode(code);
    }
}
