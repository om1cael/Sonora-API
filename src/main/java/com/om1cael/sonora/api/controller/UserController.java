package com.om1cael.sonora.api.controller;

import com.om1cael.sonora.api.dto.TokenDTO;
import com.om1cael.sonora.api.exception.EntityFoundException;
import com.om1cael.sonora.api.model.User;
import com.om1cael.sonora.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    private ResponseEntity<TokenDTO> create(@RequestBody @Valid User user) throws EntityFoundException {
        TokenDTO tokenDTO = userService.create(user);
        return new ResponseEntity<>(tokenDTO, HttpStatus.CREATED);
    }
}
