package com.example.repository;

import com.example.model.Role;
import com.example.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(RoleName name);
}
