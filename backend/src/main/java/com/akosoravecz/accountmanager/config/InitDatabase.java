package com.akosoravecz.accountmanager.config;

import com.akosoravecz.accountmanager.model.user.Role;
import com.akosoravecz.accountmanager.model.user.User;
import com.akosoravecz.accountmanager.repository.RoleRepository;
import com.akosoravecz.accountmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class InitDatabase implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

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
        adminUser.setPassword("admin");
        adminUser.setRole(Set.of(adminRole));

        User accountantUser = new User();
        accountantUser.setName("accountant");
        accountantUser.setUsername("accountant");
        accountantUser.setPassword("accountant");
        accountantUser.setRole(Set.of(accountantRole));

        User userUser = new User();
        userUser.setName("user");
        userUser.setUsername("user");
        userUser.setPassword("user");
        userUser.setRole(Set.of(userRole));

        userRepository.save(adminUser);
        userRepository.save(accountantUser);
        userRepository.save(userUser);
    }
}
