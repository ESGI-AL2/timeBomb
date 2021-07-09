package fr.esgi.timebomb.domain;

import lombok.Data;

import java.util.List;

@Data
public class GameDTO {
    private List<Player> players;

}
