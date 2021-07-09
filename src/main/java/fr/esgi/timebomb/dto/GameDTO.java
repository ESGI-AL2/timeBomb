package fr.esgi.timebomb.dto;

import fr.esgi.timebomb.domain.Player;
import lombok.Data;

import java.util.List;

@Data
public class GameDTO {
    private List<Player> players;

}
