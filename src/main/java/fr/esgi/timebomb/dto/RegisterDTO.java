package fr.esgi.timebomb.dto;

import fr.esgi.timebomb.domain.Role;
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
