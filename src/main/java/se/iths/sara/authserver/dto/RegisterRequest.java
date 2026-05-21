package se.iths.sara.authserver.dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    @Column(unique = true, nullable = false)
    @Email(message = "Ogiltig email")
    @NotBlank(message = "Email krävs")
    private String username;

    @Column(nullable = false)
    @NotBlank(message = "Lösenord krävs")
    @Size(min = 8)
    private String password;

}
