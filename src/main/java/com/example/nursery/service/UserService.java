package com.example.nursery.service;

import com.example.nursery.model.User;
import com.example.nursery.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User saveUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("User with this email already exists.");
        }
        
        // Ensure bidirectional relationship is set if userProfile exists
        if (user.getUserProfile() != null) {
            user.getUserProfile().setUser(user);
        }

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedUser.getName());
                    existing.setEmail(updatedUser.getEmail());
                    existing.setLocation(updatedUser.getLocation());
                    if (updatedUser.getUserProfile() != null) {
                        updatedUser.getUserProfile().setUser(existing);
                        existing.setUserProfile(updatedUser.getUserProfile());
                    }
                    return userRepository.save(existing);
                })
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> getAllUsersSortedByName() {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public Page<User> getPaginatedUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }

    public List<User> getUsersByProvinceCode(String code) {
        return userRepository.findByLocation_Province_Code(code);
    }

    public List<User> getUsersByProvinceName(String name) {
        return userRepository.findByLocation_Province_Name(name);
    }
}
