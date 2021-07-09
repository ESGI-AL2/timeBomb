package fr.esgi.timebomb.service;


import fr.esgi.timebomb.domain.Card;
import fr.esgi.timebomb.domain.Game;
import fr.esgi.timebomb.domain.GameDTO;
import fr.esgi.timebomb.domain.Player;
import fr.esgi.timebomb.repository.GameRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class GameService {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private CardService cardService;
    @Autowired
    private PlayerService playerService;
    public Optional<Game> getGame(final int id) {
        return gameRepository.findById(id);
    }

    public Iterable<Game> getGames() {
        return gameRepository.findAll();
    }

    public Game saveGame(Game game) {Game savedGame = gameRepository.save(game);
        return savedGame;
    }

    public Game updateGame(final int idGame, final int idPlayer) {
        Player player = playerService.getPlayer(idPlayer).get();
        Card card = cardService.randomDeleteCardFromPlayer(idPlayer);
        Game game = this.getGame(idGame).get();
        List<Player> players = playerService.getPlayersByGameId(game.getId());
        game.setNextPlayer(player);

        if (card.getValue() == Card.Value.BOMB) {
            game.setState(Game.State.MORIARTY_WIN);
            this.saveGame(game);
            return game;
        }
        else {
            if (card.getValue() == Card.Value.COLOR) {
                game.setColor_find(game.getColor_find() +1);
                if (game.getColor_find() == players.size()) {
                    game.setState(Game.State.SHERLOCK_WIN);
                    this.saveGame(game);
                    return game;
                }
            }
            game.setRound(game.getRound() + 1);
            if (game.getRound() == players.size() + 1) {
                game.setStep(game.getStep()+1);
                if (game.getStep() == 5) {
                    game.setState(Game.State.MORIARTY_WIN);
                    this.saveGame(game);
                    return game;
                }
                else {
                    game.setRound(1);
                    this.initCards(game.getId());
                }
            }
            this.saveGame(game);
            return game;
        }
    }
    private Game initCards(int id) {

        Game game = this.getGame(id).get();
        List<Card> oldCards = cardService.getCardsByGameId(id);
        List<Player> players = playerService.getPlayersByGameId(game.getId());
        for (Card card : oldCards){
            cardService.deleteCard(card.getId());
        }
        int remaining_color = players.size() - game.getColor_find();
        List<Card> cards = new ArrayList<>();
        for (int i=0; i< remaining_color; i++) {
            Card card = new Card (Card.Value.COLOR);
            cards.add(card);
        }
        cards.add(new Card (Card.Value.BOMB));
        int remaining_ordinary = (players.size() * (6-game.getStep())) - (1 + remaining_color);
        for (int i=0; i< remaining_ordinary; i++) {
            Card card = new Card (Card.Value.ORDINARY);
            cards.add(card);
        }
        Collections.shuffle(cards);
        int count_player = 0;

        for (Card card : cards) {
            card.setPlayer(players.get(count_player%players.size()));
            count_player ++;
            cardService.saveCard(card);
        }
        return game;
    }

    public Game initGame (GameDTO gameDto) {

        Game game = new Game ();
        game.setRound(1);
        game.setStep(1);
        game.setColor_find(0);
        game.setState(Game.State.IN_GAME);
        this.saveGame(game);
        List<Player> players = gameDto.getPlayers();
        List<Player.Team> roles = new ArrayList<>();
        roles.add(Player.Team.MORIARTY);
        roles.add(Player.Team.MORIARTY);
        roles.add(Player.Team.SHERLOCK);
        roles.add(Player.Team.SHERLOCK);
        roles.add(Player.Team.SHERLOCK);
        if (players.size() == 6) {
            roles.add(Player.Team.SHERLOCK);
        }
        else if (players.size() > 6) {
            roles.add(Player.Team.SHERLOCK);
            roles.add(Player.Team.SHERLOCK);
            roles.add(Player.Team.MORIARTY);
        }
        Collections.shuffle(roles);
        int count_player = 0;
        for (Player player : players) {
            player.setTeam(roles.get(count_player));
            player.setGame(game);
            count_player ++;
            playerService.savePlayer(player);
        }
        this.initCards(game.getId());
        Collections.shuffle(players);
        game.setNextPlayer(players.get(0));

        this.saveGame(game);

        for (Player player: players) {
            List<Card> cards = cardService.getCardsByPlayerId(player.getId());
            player.setCards(cards);
        }
        game.setPlayers(players);
        this.saveGame(game);
        return game;
    }

}
