package fr.esgi.timebomb.repository;


import fr.esgi.timebomb.domain.Card;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CardRepository extends CrudRepository<Card, Integer> {

    @Query (value = "SELECT * FROM card WHERE playerid = :player",
    nativeQuery = true)
    List<Card> getCardsByPlayerId(@Param("player") int playerId);

    @Query (value= "SELECT * FROM card, player WHERE card.playerid = player.id AND player.gameid = :game",
    nativeQuery = true)
    List<Card> getCardsByGameId(@Param("game") int gameId);

}
