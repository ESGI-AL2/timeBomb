package fr.esgi.timebomb.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;


@Entity
@Data
@Accessors(chain = true)
public class Card {

    public Card(Value value, Player player) {

        this.value = value;
        this.player = player;
    }

    public Card() {

    }


    public enum Value {
        COLOR, ORDINARY, BOMB
    }

    @Id
    @GeneratedValue
    private int id;
    private Value value;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="playerid",referencedColumnName="id",nullable=true,unique=false)
    private Player player;

}