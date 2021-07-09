package fr.esgi.timebomb.controller;


import fr.esgi.timebomb.domain.Player;
import fr.esgi.timebomb.exceptions.PlayerEmptyException;
import fr.esgi.timebomb.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;
    @GetMapping("")
    public Iterable<Player> getPlayers() {
        return playerService.getPlayers();
    }
    @PostMapping
    public ResponseEntity<?> createPlayer(@RequestBody Player player) throws PlayerEmptyException {
        if (player.getUsername() == null) {
            throw new PlayerEmptyException("Player Value is not empty");
        }
        Player created = playerService.savePlayer(player);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @GetMapping("/{id}")
    public Player getPlayer(@PathVariable("id") final int id) {
        Optional<Player> player = playerService.getPlayer(id);
        if(player.isPresent()) {
            return player.get();
        } else {
            return null;
        }
    }

}
