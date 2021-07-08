package fr.esgi.timebomb.repository;

import fr.esgi.timebomb.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends CrudRepository<Card, Integer> {

    @Query(value = "SELECT * FROM card WHERE playerid = :player",
            nativeQuery = true)
    List<Card> getCardsByPlayerId(@Param("player") int playerId);

}
