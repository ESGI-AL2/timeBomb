package fr.esgi.timebomb.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.esgi.timebomb.authentification.domain.Role;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Accessors(chain = true)
@JsonIgnoreProperties("cards")
public class Player {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String username;
    @OneToMany(mappedBy = "player")
    private List<Card> cards;

    public Player(String username) {
        this.username = username;
    }

    public Player (){

    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}



