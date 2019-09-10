package com.todolist.todolist.controller;

import com.todolist.todolist.domain.User;
import com.todolist.todolist.exception.ResourceNotFoundException;
import com.todolist.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public ResponseEntity<?> getAll(Pageable pageable) {
//        Page<User>
        return new ResponseEntity<>(userRepository.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
        userRepository.findById(user.getId()).map(dbUser -> {
            dbUser.setFirstName(user.getFirstName());
            dbUser.setSecondName(user.getSecondName());
            return new ResponseEntity<>(userRepository.save(dbUser), HttpStatus.OK);
        }).orElse(null);
        return new ResponseEntity<>(new ResourceNotFoundException("Resource not found"), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeUser(@PathVariable Long id) {
        userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }).orElse(null);
        return new ResponseEntity<>(new ResourceNotFoundException("Resource not found"), HttpStatus.NOT_FOUND);
    }
}
