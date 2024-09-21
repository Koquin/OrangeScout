package com.orangescout.Orange.Scout.service;

import com.orangescout.Orange.Scout.exception.UserAlreadyExists;
import com.orangescout.Orange.Scout.exception.UserNotFoundException;
import com.orangescout.Orange.Scout.model.User;
import com.orangescout.Orange.Scout.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User registerUser(User user){
        if (userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new UserAlreadyExists("User already exists");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);

    }

    public void deleteUser(Long id){
        if (!userRepository.existsById(id)){
            throw new UserNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );
    }
}
