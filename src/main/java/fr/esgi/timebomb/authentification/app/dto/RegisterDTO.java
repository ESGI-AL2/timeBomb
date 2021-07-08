package fr.esgi.timebomb.authentification.app.dto;

import fr.esgi.timebomb.authentification.domain.Role;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RegisterDTO {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private Role role;
}
