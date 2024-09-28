package com.orangescout.Orange.Scout.controller;

import com.orangescout.Orange.Scout.dto.LoginRequest;
import com.orangescout.Orange.Scout.dto.LoginResponse;
import com.orangescout.Orange.Scout.dto.RegisterRequest;
import com.orangescout.Orange.Scout.model.User;
import com.orangescout.Orange.Scout.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        // Chamada ao serviço de autenticação que valida as credenciais
        String token = authenticationService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());

        // Retorna o token dentro de um objeto de resposta
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        try {
            User user = new User();
            user.setEmail(registerRequest.getEmail());
            user.setPassword(registerRequest.getPassword());
            authenticationService.register(user);
            return new ResponseEntity<>("User successfully registered", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}

