package com.udemy.elearning.services;

import com.udemy.elearning.dto.ProfileRequest;
import com.udemy.elearning.models.Profile;
import com.udemy.elearning.models.UpgradeRoleStatus;
import com.udemy.elearning.models.User;
import com.udemy.elearning.repository.ProfileRepository;
import com.udemy.elearning.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;


@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private UserRepository userRepository;
    private static final Logger logger = LogManager.getLogger(ProfileService.class);

    public Profile updateProfile(long id, ProfileRequest profileRequest) {
        Profile profile = profileRepository.findById(id).orElseThrow(()->new NotFoundException("Profile not found"));
        User user = userRepository.findById(profileRequest.getUserId()).orElseThrow(()->new RuntimeException("User not found"));

        logger.info("profile {}", profileRequest);
        profile.setAvatar(profileRequest.getAvatar());
        profile.setCurrentWorkPlace(profileRequest.getCurrentWorkPlace());
        profile.setNickName(profileRequest.getNickName());
        profile.setWorkExperience(profileRequest.getWorkExperience());
        profile.setUser(user);
        return profileRepository.save(profile);
    }

    public Profile saveProfile(ProfileRequest profileRequest){
        Profile profile = new Profile();
        profile.setAvatar(profileRequest.getAvatar());
        profile.setCurrentWorkPlace(profileRequest.getCurrentWorkPlace());
        profile.setNickName(profileRequest.getNickName());
        profile.setWorkExperience(profileRequest.getWorkExperience());
        profile.setUpgradeRoleStatus(UpgradeRoleStatus.INITIAL);
        User user = userRepository.findById(profileRequest.getUserId()).orElseThrow(()->new RuntimeException("User not found"));
        profile.setUser(user);
        return  profileRepository.save(profile);
    }

    public Profile getProfile(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(()->new RuntimeException("user not found"));
        return user.getProfile();
    }

}
