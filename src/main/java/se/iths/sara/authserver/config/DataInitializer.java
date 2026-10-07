package se.iths.sara.authserver.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import se.iths.sara.authserver.entity.AppUser;
import se.iths.sara.authserver.entity.Role;
import se.iths.sara.authserver.repository.AppUserRepository;

@Configuration
@Profile("local")
public class DataInitializer {

    @Bean
    CommandLineRunner createUsers(
            AppUserRepository appUserRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            String adminUsername = "admin@webshop.se";

            if (!appUserRepository.existsByUsername(adminUsername)) {
                AppUser admin = new AppUser();

                admin.setUsername(adminUsername);
                admin.setPassword(passwordEncoder.encode("admin12345"));
                admin.setRole(Role.ADMIN);

                appUserRepository.save(admin);

                System.out.println("Local admin user created: " + adminUsername);
            }

            String userUsername = "user@webshop.se";

            if (!appUserRepository.existsByUsername(userUsername)) {
                AppUser user = new AppUser();

                user.setUsername(userUsername);
                user.setPassword(passwordEncoder.encode("user12345"));
                user.setRole(Role.USER);

                appUserRepository.save(user);

                System.out.println("Local regular user created: " + userUsername);
            }
        };
    }
}