package fr.esgi.timebomb;

import fr.esgi.timebomb.dao.CardDao;
import fr.esgi.timebomb.domain.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class TimeBombApplication {

    @Autowired
    private CardDao cardRepository;

/*    @PostConstruct
    public void initCard(){
        List<Card> cards = Stream.of(
                new Card(Card.Value.BOMB),
                new Card(Card.Value.ORDINARY),
                new Card(Card.Value.COLOR)
        ).collect(Collectors.toList());
        cardRepository.saveAll(cards);
    }*/

    public static void main(String[] args) {
        SpringApplication.run(TimeBombApplication.class, args);
    }

}
