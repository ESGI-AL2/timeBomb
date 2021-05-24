package fr.esgi.timebomb.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Card {

    public Card() {

    }

    public enum Value {
        COLOR, ORDINARY, BOMB
    }

    @Id
    @GeneratedValue
    private int id;
    private Value value;

    public Card(Value value) {
        this.value = value;
    }
}
