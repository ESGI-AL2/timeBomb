package fr.esgi.timebomb.controller;

import fr.esgi.timebomb.domain.Card;
import fr.esgi.timebomb.domain.Player;
import fr.esgi.timebomb.exceptions.CardEmptyException;
import fr.esgi.timebomb.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@RestController
@RequestMapping("cards")
public class CardController {

    @Autowired
    private CardService cardService;


    @GetMapping("")
    public Iterable<Card> getCards() {
        return cardService.getCards();
    }

    @GetMapping("/playerid/{id}")
    public List<Card> getCardsByPlayerId (@PathVariable("id") int playerId) {
        return cardService.getCardsByPlayerId(playerId);
    }

    @PostMapping
    public ResponseEntity<?> createCard(@RequestBody Card card) throws CardEmptyException {
        if (card.getValue() == null) {
            throw new CardEmptyException("Card Value is not empty");
        }
        Card created = cardService.saveCard(card);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).build();

    }

    @GetMapping("/{id}")
    public Card getCard(@PathVariable("id") final int id) {
        Optional<Card> card = cardService.getCard(id);
        if(card.isPresent()) {
            return card.get();
        } else {
            return null;
        }
    }

    @PutMapping("/{id}")
    public Card updateCard(@PathVariable("id") final int id, @RequestBody Card card) {
        Optional<Card> c = cardService.getCard(id);
        if(c.isPresent()) {
            Card currentCard = c.get();
            Player player = card.getPlayer();
            currentCard.setPlayer(player);
            return currentCard;
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable("id") final int id) {
        cardService.deleteCard (id);
    }

    @DeleteMapping("playerid/{id}/randomdelete")
    public Card randomDeleteCardFromPlayer (@PathVariable("id") final int id) {
        List<Card> cards = cardService.getCardsByPlayerId(id);
        Random randomizer = new Random();
        Card deleteCard = cards.get(randomizer.nextInt(cards.size()));
        cardService.deleteCard( deleteCard.getId());
        return deleteCard;
    }



}
