package com.orangescout.Orange.Scout.security;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil; // Classe que gera e valida o JWT

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl; // UserDetailsService para carregar o usuário

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        System.out.println("Authorization Header: " + token);

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Remove o "Bearer "
            String email = jwtUtil.extractUsername(token);

            System.out.println("Extracted email: " + email);

            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                MyUserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(email);
                System.out.println("UserDetails loaded for: " + email);
                System.out.println("Username: " + userDetails.getEmail());

                // Aqui você pode validar o token
                if (jwtUtil.validateToken(token, userDetails)) {
                    System.out.println("Token is valid. Authenticating user: " + email);

                    // Autentica o usuário
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails.getEmail(), null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    System.out.println("Invalid token for user: " + email);
                }
            } else {
                System.out.println("Username is null or authentication already exists.");
            }
        } else {
            System.out.println("No valid Authorization header found.");
        }

        chain.doFilter(request, response);
    }

}
