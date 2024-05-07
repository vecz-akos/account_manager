package com.akosoravecz.accountmanager.config;

import com.akosoravecz.accountmanager.model.user.Role;
import com.akosoravecz.accountmanager.model.user.User;
import com.akosoravecz.accountmanager.repository.user.RoleRepository;
import com.akosoravecz.accountmanager.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class InitDatabase implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Role adminRole = new Role();
        adminRole.setName("admin");
        adminRole.setDescription("Admin privileges.");

        Role accountantRole = new Role();
        accountantRole.setName("accountant");
        accountantRole.setDescription("Accounter privileges.");

        Role userRole = new Role();
        userRole.setName("user");
        userRole.setDescription("Read privileges.");

        roleRepository.save(adminRole);
        roleRepository.save(accountantRole);
        roleRepository.save(userRole);


        User adminUser = new User();
        adminUser.setName("admin");
        adminUser.setUsername("admin");
        adminUser.setPassword(passwordEncoder.encode("admin"));
        adminUser.setRoles(Set.of(adminRole));

        User accountantUser = new User();
        accountantUser.setName("accountant");
        accountantUser.setUsername("accountant");
        accountantUser.setPassword(passwordEncoder.encode("accountant"));
        accountantUser.setRoles(Set.of(accountantRole));

        User userUser = new User();
        userUser.setName("user");
        userUser.setUsername("user");
        userUser.setPassword(passwordEncoder.encode("user"));
        userUser.setRoles(Set.of(userRole));

        userRepository.save(adminUser);
        userRepository.save(accountantUser);
        userRepository.save(userUser);
    }
}
