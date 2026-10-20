package se.iths.sara.authserver.dto;

import java.util.List;

public record SessionResponseDTO(
        long expiresIn,
        String subject,
        List<String> roles
) {
}
