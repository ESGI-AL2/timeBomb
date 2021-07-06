package fr.esgi.timebomb.dao;

import fr.esgi.timebomb.domain.Player;
import fr.esgi.timebomb.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Optional;

@Repository
public interface AccountRepository  extends JpaRepository<Player, Integer> {
    Optional<Player> findPlayerByUsername(String username);
}
