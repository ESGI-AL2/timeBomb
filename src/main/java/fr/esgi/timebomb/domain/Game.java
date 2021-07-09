package fr.esgi.timebomb.domain;


import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
public class Game {


    public enum State {
        MORIARTY_WIN, SHERLOCK_WIN, IN_GAME
    }

    @Id
    @GeneratedValue
    private int id;
    private State state;
    private int step;
    private int round;
    private int color_find;

    @OneToMany(mappedBy = "game")
    private List<Player> players;

    @OneToOne
    @JoinColumn( name="next_player", nullable=true )
    private Player nextPlayer;

    public Game() {
        this.step = 1;
        this.round = 1;
        this.color_find = 0;
        this.state = State.IN_GAME;
    }

}
