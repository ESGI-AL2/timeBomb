package fr.esgi.timebomb.service;

import fr.esgi.timebomb.domain.Card;
import fr.esgi.timebomb.repository.CardRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public Optional<Card> getCard(final int id) {
        return cardRepository.findById(id);
    }

    public Iterable<Card> getCards() {
        return cardRepository.findAll();
    }

    public Card saveCard(Card card) {Card savedCard = cardRepository.save(card);
        return savedCard;
    }
    public List<Card> getCardsByPlayerId (int playerId) {
        return cardRepository.getCardsByPlayerId(playerId);
    }
    public void deleteCard(final int id) {
        cardRepository.deleteById(id);
    }


}

