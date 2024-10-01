package com.orangescout.Orange.Scout.service;

import com.orangescout.Orange.Scout.model.User;
import com.orangescout.Orange.Scout.repository.UserRepository;
import com.orangescout.Orange.Scout.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String authenticate(String email, String password) {
        // Verifica se o usuário existe
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Verifica se a senha fornecida está correta
            if (passwordEncoder.matches(password, user.getPassword())) {
                // Gera e retorna o token JWT
                return jwtUtil.generateToken(user); // Passando o objeto User
            }
        }
        throw new RuntimeException("Invalid Credentials");
    }


    public void register(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Usuário já existe com esse email");
        }
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        System.out.println("User name:" + user.getUsername());
        userRepository.save(user);
    }

}

