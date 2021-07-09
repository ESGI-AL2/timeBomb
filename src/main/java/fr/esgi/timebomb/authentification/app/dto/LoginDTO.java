package fr.esgi.timebomb.authentification.app.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class LoginDTO {

    @NotNull
    private String username;
    @NotNull
    private String password;
}
