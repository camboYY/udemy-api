package com.udemy.elearning.repository;

import com.udemy.elearning.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository  extends JpaRepository<Profile,Long> {
    @Query(value = "select * from profiles where user_id = :userId", nativeQuery = true)
     Profile findByUserId(Long userId);
}

