package fr.esgi.timebomb.controller;

import fr.esgi.timebomb.dao.CardDao;
import fr.esgi.timebomb.domain.Card;
import fr.esgi.timebomb.exceptions.CardEmptyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private CardDao cardDao;

    @GetMapping
    public ResponseEntity<List<Card>> listCard() {
        return ok(cardDao.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> findById(@PathVariable int id) {
        return cardDao.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createCard(@RequestBody Card card) throws CardEmptyException {
        if (card.getValue() == null) {
            throw new CardEmptyException("Carde Value is not empty");
        }
        Card created = cardDao.save(card);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).build();

    }
}
