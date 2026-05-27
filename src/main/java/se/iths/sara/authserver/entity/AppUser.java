package se.iths.sara.authserver.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "app_users")
@Getter
@Setter
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @Email(message = "Ogiltig email")
    @NotBlank(message = "Email krävs")
    private String username;

    @Column(nullable = false)
    @NotBlank(message = "Lösenord krävs")
    @Size(min = 8, message = "Lösenord måste vara minst 8 tecken")
    private String password;

    @Column(nullable = false)
    private boolean consent;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public AppUser() {
        this.role = Role.USER;
    }
}
