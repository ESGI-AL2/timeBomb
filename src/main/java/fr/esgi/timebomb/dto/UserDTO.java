package fr.esgi.timebomb.dto;

import fr.esgi.timebomb.authentification.domain.Role;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String username;
    private Role role;
}
