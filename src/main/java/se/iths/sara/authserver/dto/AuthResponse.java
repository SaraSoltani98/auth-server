package se.iths.sara.authserver.dto;

import java.util.List;

public record AuthResponse(
        String accessToken,
        long expiresIn,
        String subject,
        List<String> roles
) {
}