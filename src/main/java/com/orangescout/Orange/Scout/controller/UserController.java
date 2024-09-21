package com.orangescout.Orange.Scout.controller;

import com.orangescout.Orange.Scout.exception.EditUserException;
import com.orangescout.Orange.Scout.exception.UserAlreadyExists;
import com.orangescout.Orange.Scout.exception.UserNotFoundException;
import com.orangescout.Orange.Scout.model.User;
import com.orangescout.Orange.Scout.repository.UserRepository;
import com.orangescout.Orange.Scout.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        try {
            User saveUser = userService.registerUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (UserAlreadyExists e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> editUser(@RequestBody User user, @PathVariable Long id){
        try {
            User editedUser = userService.getUserById(id);
            editedUser.setEmail(user.getEmail());
            editedUser.setUsername(user.getUsername());
            editedUser.setPassword(user.getPassword());
            userService.registerUser(editedUser);
            return new ResponseEntity<>(editedUser, HttpStatus.OK);
        } catch (EditUserException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id){
        if (!userRepository.existsById(id)){
            throw new UserNotFoundException("User not found");
        }
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.GONE);
    }


}
