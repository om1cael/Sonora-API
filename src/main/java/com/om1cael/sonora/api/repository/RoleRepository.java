package com.om1cael.sonora.api.repository;

import com.om1cael.sonora.api.model.Role;
import com.om1cael.sonora.api.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(Roles name);
}
