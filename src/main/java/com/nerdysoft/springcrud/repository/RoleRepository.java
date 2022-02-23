package com.nerdysoft.springcrud.repository;

import com.nerdysoft.springcrud.entity.Role;
import com.nerdysoft.springcrud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByCode(String code);
}
