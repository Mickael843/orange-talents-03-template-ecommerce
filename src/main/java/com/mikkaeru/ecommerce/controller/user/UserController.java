package com.mikkaeru.ecommerce.controller.user;

import com.mikkaeru.ecommerce.dto.in.user.UserRequest;
import com.mikkaeru.ecommerce.repository.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid UserRequest userRequest) {
        userRepository.save(userRequest.toModel());
        return ResponseEntity.ok().build();
    }
}
