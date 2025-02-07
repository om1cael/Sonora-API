package com.om1cael.sonora.api.service;

import com.om1cael.sonora.api.dto.TokenDTO;
import com.om1cael.sonora.api.exception.EntityFoundException;
import com.om1cael.sonora.api.model.Role;
import com.om1cael.sonora.api.model.Roles;
import com.om1cael.sonora.api.model.User;
import com.om1cael.sonora.api.repository.RoleRepository;
import com.om1cael.sonora.api.repository.UserRepository;
import com.om1cael.sonora.api.utils.JwtUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public TokenDTO create(User user) throws EntityFoundException {
        if(userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new EntityFoundException("User already exists");
        }

        Optional<Role> role = roleRepository.findByName(Roles.ROLE_USER);
        if(role.isEmpty()) {
            throw new EntityNotFoundException("Role user not found");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of(role.get()));
        userRepository.save(user);

        String token = jwtUtils.createToken(user.getUsername());
        return new TokenDTO(token);
    }
}
