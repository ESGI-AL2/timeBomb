package fr.esgi.timebomb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Accessors(chain = true)
public class Player {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role roles;
    @ManyToMany(cascade=CascadeType.ALL)
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


    public Player() {

    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}



