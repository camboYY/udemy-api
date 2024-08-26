package com.udemy.elearning.services;

import com.udemy.elearning.dto.ProfileRequest;
import com.udemy.elearning.models.Profile;
import com.udemy.elearning.repository.ProfileRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;


@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    private static final Logger logger = LogManager.getLogger(ProfileService.class);

    public Profile updateProfile(long id, ProfileRequest profileRequest) {
        Profile profile = profileRepository.findById(id).orElseThrow(()->new NotFoundException("Profile not found"));
        logger.info("profile {}", profileRequest);
        return profileRepository.save(profile);
    }

    public Profile saveProfile(ProfileRequest profileRequest){
        Profile profile = new Profile();
        profile.setAvatar(profileRequest.getAvatar());
        profile.setCurrentWorkPlace(profileRequest.getCurrentWorkPlace());
        profile.setNickName(profileRequest.getNickName());
        profile.setWorkExperience(profileRequest.getWorkExperience());
        return  profileRepository.save(profile);
    }
}
