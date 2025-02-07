package com.om1cael.sonora.api;

import com.om1cael.sonora.api.model.Role;
import com.om1cael.sonora.api.model.Roles;
import com.om1cael.sonora.api.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataSetup {

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void setup() {
        setupRole(Roles.ROLE_USER);
        setupRole(Roles.ROLE_ARTIST);
        setupRole(Roles.ROLE_ADMIN);
    }

    private void setupRole(Roles name) {
        Role repositoryRole = roleRepository.findByName(name).orElse(null);
        if(repositoryRole != null) return;

        Role newRole = new Role();
        newRole.setName(name);
        roleRepository.save(newRole);
    }

}
