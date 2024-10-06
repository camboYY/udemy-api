package com.udemy.elearning.repository;

import com.udemy.elearning.models.UpgradeRoleStatus;
import com.udemy.elearning.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByPhoneNumber(String phoneNumber);

    @Query( value = "select u.* from users u left join profiles p  on u.id = p.user_id where p.upgrade_role_status = :upgradeRoleStatus", nativeQuery=true)
    List<User> getUpgradeRoleStatus(UpgradeRoleStatus upgradeRoleStatus);
}
