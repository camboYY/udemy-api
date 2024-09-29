package com.udemy.elearning.bootstrap;


import com.udemy.elearning.models.ERole;
import com.udemy.elearning.models.Role;
import com.udemy.elearning.repository.RoleRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;


    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.loadRoles();
    }

    private void loadRoles() {
        ERole[] roleNames = new ERole[] { ERole.ROLE_USER, ERole.ROLE_STUDENT, ERole.ROLE_ADMIN,ERole.ROLE_TEACHER,ERole.ROLE_MODERATOR };
        Map<ERole, String> roleDescriptionMap = Map.of(
                ERole.ROLE_USER, "Default user role",
                ERole.ROLE_ADMIN, "Administrator role",
                ERole.ROLE_STUDENT, "Student role",
                ERole.ROLE_TEACHER,"Teacher role",
                ERole.ROLE_MODERATOR, "Moderator role",
                ERole.SUPER_ADMIN,"Super admin role"
        );

        Arrays.stream(roleNames).forEach((roleName) -> {
            Optional<Role> optionalRole = roleRepository.findByName(roleName);

            optionalRole.ifPresentOrElse(System.out::println, () -> {
                Role roleToCreate = new Role();

                roleToCreate.setName(roleName);
                roleToCreate.setDescription(roleDescriptionMap.get(roleName));

                roleRepository.save(roleToCreate);
            });
        });
    }
}

