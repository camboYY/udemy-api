package com.udemy.elearning.services;

import com.udemy.elearning.dto.SignupRequest;
import com.udemy.elearning.dto.UpgradeRoleRequest;
import com.udemy.elearning.dto.UpgradingRoleRequest;
import com.udemy.elearning.exceptions.UserNotFoundException;
import com.udemy.elearning.mapper.CourseByResponse;
import com.udemy.elearning.mapper.UpgradeRoleResponse;
import com.udemy.elearning.models.*;
import com.udemy.elearning.repository.ProfileRepository;
import com.udemy.elearning.repository.RoleRepository;
import com.udemy.elearning.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ProfileRepository profileRepository;
    private static final Logger logger = LogManager.getLogger(UserService.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<User> getUsers(Pageable pageable){
        return this.userRepository.findAll(pageable);
    }

    public CourseByResponse findById(Long id){
        User user = userRepository.findById(id).orElseThrow(()->new NotFoundException("User not found"));
        return new CourseByResponse(user);
    }
    public Boolean validateEmail(String keyValue){
        return userRepository.existsByEmail(keyValue);
    }
    public Boolean validateUsername(String keyValue){
        return userRepository.existsByUsername(keyValue);
    }
    public Boolean validatePhoneNumber(String keyValue){
        return userRepository.existsByPhoneNumber(keyValue);
    }
    public User getUserReview(Long id){
        return userRepository.findById(id).orElseThrow(()->new NotFoundException("User not found"));
    }


    public User createAdministrator(SignupRequest input) {
        Optional<Role> optionalRole = roleRepository.findByName(ERole.ROLE_ADMIN);

        if (optionalRole.isEmpty()) {
            return null;
        }

        User user = new User();
        user.setName(input.getName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setRole(optionalRole.get());
        user.setPhoneNumber(input.getPhoneNumber());
        user.setUsername(input.getUsername());

        return userRepository.save(user);

    }

    @Transactional
    public UpgradeRoleResponse upgradeRole(UpgradeRoleRequest roleRequest) {
        Optional<Role> role = roleRepository.findByName(roleRequest.getRole());
        Optional<User> user =  userRepository.findById(roleRequest.getUserId());
      if(user.isPresent() && role.isPresent()) {
          user.get().setRole(role.get());
        User user1 = userRepository.save(user.get());
         Profile profile = profileRepository.findByUserId(roleRequest.getUserId());
         profile.setUpgradeRoleStatus(UpgradeRoleStatus.SUCCESS);
         profileRepository.save(profile);
        return new UpgradeRoleResponse(user1.getRole().getName());
      }
      throw new UserNotFoundException("user is not found.");
    }

    public Profile becomingTeacher(UpgradingRoleRequest upgradingRoleRequest) throws BadRequestException {
        Optional<Profile> profile = profileRepository.findById(upgradingRoleRequest.getProfileId());
        logger.info("becomingTeacher :{}",upgradingRoleRequest);
        logger.info("profile :{}",profile);

        if(profile.isPresent()){
            profile.get().setUpgradeRoleStatus(UpgradeRoleStatus.PENDING);
            return profileRepository.save(profile.get());
        }
        throw new BadRequestException("bad request");
    }

    public List<User> getListOfUserRequestingNewRole() {
        return userRepository.getUpgradeRoleStatus(UpgradeRoleStatus.PENDING);
    }

    public boolean checkIfUserApplicableRole (String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.filter(value -> Arrays.asList(ERole.ROLE_TEACHER, ERole.ROLE_SUPER_ADMIN, ERole.ROLE_ADMIN).contains(value.getRole().getName())).isPresent();
    }

}
