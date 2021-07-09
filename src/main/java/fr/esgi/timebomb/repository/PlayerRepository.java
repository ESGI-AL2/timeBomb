package fr.esgi.timebomb.repository;




import fr.esgi.timebomb.domain.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer> {

    @Query(value = "SELECT * FROM player WHERE gameid = :game",
            nativeQuery = true)
    List<Player> getPlayersByGameId(@Param("game") int gameId);
}
