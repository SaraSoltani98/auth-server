package se.iths.sara.authserver.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @Email(message = "Ogiltig email")
    @NotBlank(message = "Email krävs")
    private String username;

    @NotBlank(message = "Lösenord krävs")
    private String password;
}
