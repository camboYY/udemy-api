package com.udemy.elearning.repository;

import com.udemy.elearning.dto.ProfileRequest;
import com.udemy.elearning.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository  extends JpaRepository<Profile,Long> {
}
