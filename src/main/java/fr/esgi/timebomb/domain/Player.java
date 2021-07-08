package fr.esgi.timebomb.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private String password;
    @Enumerated(EnumType.STRING)
    private Role roles;
    @OneToMany(mappedBy = "player")
    private List<Card> cards;

    public Player(String username, String password, Role roles, List<Card> cards) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.cards = cards;
    }
    public Player(String username, String password, Role roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }


    public Player(String username) {
        this.username = username;
    }

    public Player (){

    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}



