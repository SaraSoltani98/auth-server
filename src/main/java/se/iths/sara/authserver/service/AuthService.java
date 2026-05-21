package se.iths.sara.authserver.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.iths.sara.authserver.dto.AuthResponse;
import se.iths.sara.authserver.dto.LoginRequest;
import se.iths.sara.authserver.dto.RegisterRequest;
import se.iths.sara.authserver.entity.AppUser;
import se.iths.sara.authserver.repository.AppUserRepository;

@Service
public class AuthService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(AppUserRepository appUserRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {

        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public void register(RegisterRequest request) {
        if (appUserRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Användaren finns redan.");
        }
        AppUser user = new AppUser();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        appUserRepository.save(user);
    }

    public AuthResponse login(LoginRequest request) {
        AppUser user = appUserRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Fel username eller lösenord"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Fel username eller lösenord");
        }
        String token = jwtService.generateToken(user.getUsername(), "USER");

        return new AuthResponse(token);
    }
}
// JWT authentication implementation by Sara