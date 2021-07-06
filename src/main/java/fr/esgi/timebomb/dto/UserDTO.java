package fr.esgi.timebomb.dto;

import fr.esgi.timebomb.domain.Card;
import fr.esgi.timebomb.domain.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

    private Long id;
    private String username;
    private Role role;
    private List<Card> cards;
}
