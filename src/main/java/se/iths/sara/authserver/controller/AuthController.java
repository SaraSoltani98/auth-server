package se.iths.sara.authserver.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.sara.authserver.dto.AuthResponse;
import se.iths.sara.authserver.dto.LoginRequest;
import se.iths.sara.authserver.dto.RegisterRequest;
import se.iths.sara.authserver.service.AuthService;
import se.iths.sara.authserver.service.JwtService;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtService;

    public AuthController(AuthService authService, JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok("Användare registerad.");
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @SecurityRequirement(name = "mySecurity")
    @GetMapping("/profile")
    public String profile() {
        return "Secret page";
    }

    @GetMapping("/jwks")
    public Map<String, Object> jwks() {
        return jwtService.getJwks();
    }

    @PutMapping("/users/{username}/admin")
    public String makeAdmin(@PathVariable String username) {
        authService.makeAdmin(username);
        return username + " is now ADMIN";
    }

}
