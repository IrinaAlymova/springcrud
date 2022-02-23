package com.nerdysoft.springcrud.repository;

import com.nerdysoft.springcrud.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
