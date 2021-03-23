package com.mikkaeru.ecommerce.controller.auth;

import com.mikkaeru.ecommerce.dto.in.user.UserLogin;
import com.mikkaeru.ecommerce.dto.out.TokenResponse;
import com.mikkaeru.ecommerce.security.TokenManager;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final TokenManager tokenManager;
    private final AuthenticationManager authenticationManager;

    public AuthenticationController(TokenManager tokenManager, AuthenticationManager authenticationManager) {
        this.tokenManager = tokenManager;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public ResponseEntity<?> authenticate(@RequestBody @Valid UserLogin request) {
        UsernamePasswordAuthenticationToken authenticationToken = request.toAuthenticationToken();

        try {
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);

            String token = tokenManager.tokenGeneration(authenticate);

            return ResponseEntity.ok(new TokenResponse("Bearer", token));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
