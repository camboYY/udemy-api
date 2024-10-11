package com.udemy.elearning.mapper;

import com.udemy.elearning.models.Profile;
import lombok.Data;

@Data
public class ProfileResponse {

    private long id;

    private String avatar;

    private String nickName;

    private String workExperience;

    private String currentWorkPlace;

    public ProfileResponse (Profile profile) {
        this.setAvatar(profile.getAvatar());
        this.setCurrentWorkPlace(profile.getCurrentWorkPlace());
        this.setNickName(profile.getNickName());
        this.setWorkExperience(profile.getWorkExperience());
        this.setId(profile.getId());
    }
}
