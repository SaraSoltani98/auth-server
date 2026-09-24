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
    CommandLineRunner createAdmin(
            AppUserRepository appUserRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            String username = "admin@webshop.se";

            if (!appUserRepository.existsByUsername(username)) {
                AppUser admin = new AppUser();

                admin.setUsername(username);
                admin.setPassword(passwordEncoder.encode("admin12345"));
                admin.setRole(Role.ADMIN);

                appUserRepository.save(admin);

                System.out.println("Local admin user created: " + username);
            }
        };
    }
}