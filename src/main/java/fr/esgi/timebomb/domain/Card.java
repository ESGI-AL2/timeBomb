package fr.esgi.timebomb.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


import javax.persistence.*;


@Entity
@Data
@JsonIgnoreProperties("player")
public class Card {

    public Card(Value value, Player player) {

        this.value = value;
        this.player = player;
    }

    public Card() {

    }
    public Card (Value value) {
        this.value = value;
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