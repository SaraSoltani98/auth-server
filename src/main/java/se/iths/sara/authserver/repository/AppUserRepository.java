package se.iths.sara.authserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.sara.authserver.entity.AppUser;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);

    boolean existsByUsername(String username);
}
