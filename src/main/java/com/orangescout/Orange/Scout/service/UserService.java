package com.orangescout.Orange.Scout.service;

import com.orangescout.Orange.Scout.model.User;
import com.orangescout.Orange.Scout.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService (BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public void registerUser(String username, String rawPassword, String email){
        String encodedPassword = bCryptPasswordEncoder.encode(rawPassword);
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(encodedPassword);

        userRepository.save(user);
    }
}
