package fr.esgi.timebomb.controller;


import fr.esgi.timebomb.domain.Game;
import fr.esgi.timebomb.dto.GameDTO;
import fr.esgi.timebomb.exceptions.GameEmptyException;
import fr.esgi.timebomb.service.CardService;
import fr.esgi.timebomb.service.GameService;
import fr.esgi.timebomb.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.Optional;
@RestController
@RequestMapping("/api/games")
public class GameController {
    @Autowired
    private GameService gameService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private CardService cardService;

    @GetMapping("")
    public Iterable<Game> getGames() {
        return gameService.getGames();
    }

    @PostMapping
    public ResponseEntity<?> createGame(@RequestBody GameDTO gameDto) throws GameEmptyException {
        if (gameDto.getPlayers() == null) {
            throw new GameEmptyException("Game Value is not empty");
        }
        Game created = gameService.initGame(gameDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }


    @GetMapping("/{id}")
    public Game getGame(@PathVariable("id") final int id) {
        Optional<Game> game = gameService.getGame(id);
        if(game.isPresent()) {
            return game.get();
        } else {
            return null;
        }
    }

    @PutMapping("{id}/move/{playerId}")
    public ResponseEntity<?> updateGame(@PathVariable("id") final int idGame, @PathVariable("playerId") final int idPlayer) {
        Game game = gameService.updateGame(idGame, idPlayer);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(game.getId())
                .toUri();
        return ResponseEntity.created(location).body(game);
    }
}





