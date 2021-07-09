package fr.esgi.timebomb.service;


import fr.esgi.timebomb.domain.Card;
import fr.esgi.timebomb.repository.CardRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.Random;

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
    public List<Card> getCardsByGameId (int gameId) { return cardRepository.getCardsByGameId(gameId);}
    public void deleteCard(final int id) {
        cardRepository.deleteById(id);
    }

    public Card randomDeleteCardFromPlayer (final int id) {
        List<Card> cards = this.getCardsByPlayerId(id);
        Random randomizer = new Random();
        Card deleteCard = cards.get(randomizer.nextInt(cards.size()));
        this.deleteCard( deleteCard.getId());
        return deleteCard;
    }




}

