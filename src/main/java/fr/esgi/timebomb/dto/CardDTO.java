package fr.esgi.timebomb.dto;

import fr.esgi.timebomb.domain.Card;
import lombok.Data;

@Data
public class CardDTO {

    private Card.Value value;
    private String playerId;
}
