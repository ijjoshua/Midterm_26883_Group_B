package com.example.nursery.service;

import com.example.nursery.model.UserProfile;
import com.example.nursery.repository.UserProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public UserProfile saveUserProfile(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    public List<UserProfile> getAllUserProfiles() {
        return userProfileRepository.findAll();
    }

    public Optional<UserProfile> getUserProfileById(Long id) {
        return userProfileRepository.findById(id);
    }

    @Transactional
    public UserProfile updateUserProfile(Long id, UserProfile updatedProfile) {
        return userProfileRepository.findById(id)
                .map(existing -> {
                    existing.setPhoneNumber(updatedProfile.getPhoneNumber());
                    existing.setNationalId(updatedProfile.getNationalId());
                    existing.setUser(updatedProfile.getUser());
                    return userProfileRepository.save(existing);
                })
                .orElseThrow(() -> new IllegalArgumentException("UserProfile not found"));
    }

    @Transactional
    public void deleteUserProfile(Long id) {
        userProfileRepository.deleteById(id);
    }
}

