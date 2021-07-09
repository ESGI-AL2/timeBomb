package fr.esgi.timebomb.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Accessors(chain = true)
@JsonIgnoreProperties("game")
public class Player {
    @Id
    @GeneratedValue
    private int id;
    private String username;


    @OneToMany(mappedBy = "player")
    private List<Card> cards;

    public Player(String username, Game game, Team team) {
        this.username = username;
        this.game = game;
        this.team = team;
    }
    @ManyToOne
    @JoinColumn (name="gameid",referencedColumnName="id",nullable=false,unique=false)
    private Game game;

    public enum Team {
        MORIARTY, SHERLOCK
    }

    private Team team;
    public Player (){

    }
}
