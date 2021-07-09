package fr.esgi.timebomb.authentification.app.dto;

import fr.esgi.timebomb.authentification.domain.Role;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class RegisterDTO {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private Role role;
}
