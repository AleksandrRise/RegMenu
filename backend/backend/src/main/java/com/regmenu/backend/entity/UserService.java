package com.regmenu.backend.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create a new user
    public ResponseEntity<Object> newUser(UserEntity user) {
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    // Get the whole list of users
    public List<UserEntity> getUsers() {
        return this.userRepository.findAll();
    }

    // Update a user
    public ResponseEntity<Object> updateUser(Long id, UserEntity updatedUser) {
        Optional<UserEntity> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        UserEntity existingUser = userOptional.get();

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());
        userRepository.save(existingUser);
        return ResponseEntity.ok(existingUser);
    }

    public ResponseEntity<Object> deleteUser(Long id) {
        Optional<UserEntity> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Object> getUserById(Long id) {
        Optional<UserEntity> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        UserEntity user = userOptional.get();

        return ResponseEntity.ok(user);
    }



}
