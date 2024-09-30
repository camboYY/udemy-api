package com.udemy.elearning.bootstrap;

import com.udemy.elearning.dto.SignupRequest;
import com.udemy.elearning.models.ERole;
import com.udemy.elearning.models.Role;
import com.udemy.elearning.models.User;
import com.udemy.elearning.repository.RoleRepository;
import com.udemy.elearning.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger logger = LogManager.getLogger(AdminSeeder.class);
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public AdminSeeder(
            RoleRepository roleRepository,
            UserRepository  userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.createSuperAdministrator();
    }

    private void createSuperAdministrator() {
        SignupRequest userDto = new SignupRequest();
        userDto.setName("Super Admin");
        userDto.setEmail("super.admin@email.com");
        userDto.setPassword("123456");
        userDto.setUsername("admin");
        userDto.setPhoneNumber("85589920890");

        Optional<Role> optionalRole = roleRepository.findByName(ERole.ROLE_SUPER_ADMIN);
        boolean optionalUser = userRepository.existsByEmail(userDto.getEmail());
        logger.info("optionalRole: {}", optionalRole);
        logger.info("optionalUser: {}", optionalUser);

        if (optionalRole.isEmpty() || optionalUser) {
            return;
        }

        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(optionalRole.get());
        user.setUsername(userDto.getUsername());
        user.setPhoneNumber(userDto.getPhoneNumber());

        userRepository.save(user);
    }
}
