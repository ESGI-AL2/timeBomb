package fr.esgi.timebomb.Player;

import fr.esgi.timebomb.domain.Player;
import fr.esgi.timebomb.repository.PlayerRepository;
import fr.esgi.timebomb.service.PlayerService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.hamcrest.core.Is.is;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PlayerServiceTest {

    @InjectMocks private PlayerService playerService;

    @Mock private PlayerRepository playerRepository;

    private Player player = new Player();
    private long playerId;

    @Test
    @Order(2)
    public void should_get_list_player() {
        playerService.getPlayers();

        verify(playerRepository).findAll();
    }

    @Test
    @Order(1)
    public void should_add_player() {
        player.setUsername("USER_TEST");

        playerService.savePlayer(player);

        assertThat(player.getUsername(), is("USER_TEST"));

    }
}
