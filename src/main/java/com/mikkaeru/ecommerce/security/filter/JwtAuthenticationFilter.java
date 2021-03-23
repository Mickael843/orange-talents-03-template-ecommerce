package com.mikkaeru.ecommerce.security.filter;

import com.mikkaeru.ecommerce.model.user.User;
import com.mikkaeru.ecommerce.repository.user.UserRepository;
import com.mikkaeru.ecommerce.security.TokenManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenManager tokenManager;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(TokenManager tokenManager, UserRepository userRepository) {
        this.tokenManager = tokenManager;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = tokenRecovery(request);

        if (tokenManager.isValidToken(token)) {
            authenticateClient(token);
        }

        chain.doFilter(request, response);
    }

    private void authenticateClient(String token) {
        Long idUser = tokenManager.getIdUser(token);

        Optional<User> user = userRepository.findById(idUser);

        if (user.isPresent()) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    user.get(), null, user.get().getAuthorities()
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    private String tokenRecovery(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty() || !token.startsWith("Bearer")) {
            return null;
        }

        return token.substring(7);
    }
}
