package com.om1cael.sonora.api;

import com.om1cael.sonora.api.model.Role;
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
        setupRole("ROLE_USER");
        setupRole("ROLE_ARTIST");
        setupRole("ROLE_ADMIN");
    }

    private void setupRole(String name) {
        Role repositoryRole = roleRepository.findByName("name").orElse(null);
        if(repositoryRole != null) return;

        Role newRole = new Role();
        newRole.setName(name);
        roleRepository.save(newRole);
    }

}
