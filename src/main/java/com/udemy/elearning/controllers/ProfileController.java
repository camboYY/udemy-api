package com.udemy.elearning.controllers;

import com.udemy.elearning.dto.ProfileRequest;
import com.udemy.elearning.mapper.ProfileResponse;
import com.udemy.elearning.models.Profile;
import com.udemy.elearning.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PutMapping("/{id}")
    public ResponseEntity<ProfileResponse> updateProfile(@PathVariable Long id, @RequestBody ProfileRequest profileRequest) {
        Profile profile = profileService.updateProfile(id,profileRequest);
        ProfileResponse profileResponse = new ProfileResponse(profile);
        return ResponseEntity.ok(profileResponse);
    }

    @PostMapping("/")
    public ResponseEntity<ProfileResponse> saveProfile(@RequestBody ProfileRequest profileRequest) {
        Profile profile = profileService.saveProfile(profileRequest);
        ProfileResponse profileResponse = new ProfileResponse(profile);
        return ResponseEntity.ok(profileResponse);
    }

}
