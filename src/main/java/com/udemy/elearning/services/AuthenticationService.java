package com.udemy.elearning.services;


import com.udemy.elearning.dto.LoginRequest;
import com.udemy.elearning.dto.SignupRequest;
import com.udemy.elearning.models.ERole;
import com.udemy.elearning.models.Role;
import com.udemy.elearning.models.User;
import com.udemy.elearning.repository.RoleRepository;
import com.udemy.elearning.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    private static final Logger logger = LogManager.getLogger(AuthenticationService.class);

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder, RoleRepository roleRepository
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public User signup(SignupRequest signUpRequest) throws BadRequestException {
        logger.info("signUpRequest{}", signUpRequest);
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new BadRequestException("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Error: Email is already in use!");
        }
        if (userRepository.existsByPhoneNumber(signUpRequest.getPhoneNumber())) {
            throw new BadRequestException("Error: Phone Number is already in use!");
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),passwordEncoder.encode(signUpRequest.getPassword()), signUpRequest.getName(),
                signUpRequest.getPhoneNumber());

        String strRole = signUpRequest.getRole();
        Role roles = null;
        logger.info("role{}", strRole);

        if (strRole == null) {
            roles = (findRoleByName(ERole.ROLE_USER));
        } else {

                switch (strRole) {
                    case "superAdmin":
                        roles = (findRoleByName(ERole.ROLE_SUPER_ADMIN));
                        break;
                    case "admin":
                        roles = (findRoleByName(ERole.ROLE_ADMIN));
                        break;
                    case "mod":
                        roles = (findRoleByName(ERole.ROLE_MODERATOR));
                        break;
                    case "student":
                        roles = ( findRoleByName(ERole.ROLE_STUDENT));
                        break;
                    case "teacher":
                        roles = (findRoleByName(ERole.ROLE_TEACHER));
                        break;
                    default:
                     roles = (findRoleByName(ERole.ROLE_USER));
                }
            }

        user.setRole(roles);
        return userRepository.save(user);
    }


    public User authenticate(LoginRequest input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        return userRepository.findByUsername(input.getUsername())
                .orElseThrow();
    }

    private Role findRoleByName (ERole role) {
        Optional<Role> modRole = roleRepository.findByName(role);
        if(modRole.isEmpty()) {
            Role newRole = new Role();
            newRole.setName(role);
            return newRole;
        } else {
          return modRole.get();
        }
    }
}

