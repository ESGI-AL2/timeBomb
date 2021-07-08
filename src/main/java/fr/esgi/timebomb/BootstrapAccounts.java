package fr.esgi.timebomb;

import fr.esgi.timebomb.domain.Card;
import fr.esgi.timebomb.domain.Player;
import fr.esgi.timebomb.domain.Role;
import fr.esgi.timebomb.repository.CardRepository;
import fr.esgi.timebomb.repository.PlayerRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class BootstrapAccounts {

    private final PlayerRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final CardRepository cardRepository;

    BootstrapAccounts(PlayerRepository accountRepository, PasswordEncoder passwordEncoder, CardRepository cardRepository) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.cardRepository = cardRepository;
    }

    @EventListener
    void onStartup(ApplicationReadyEvent event) {
//        Player p1 = new Player("mvestro",passwordEncoder.encode("haribo"), Role.USER);
//        Card c1 = new Card (Card.Value.BOMB, p1);
//        Card c2 = new Card (Card.Value.COLOR, p1);
//        accountRepository.save(p1);
//        cardRepository.save(c1);
//        cardRepository.save(c2);


//        Card card = new Card().setValue(Card.Value.BOMB);
//        Card card1 = new Card().setValue(Card.Value.COLOR);
//        List.of(
////                new Player().setUsername("mvestro").setPassword(passwordEncoder.encode("haribo")).setRoles(List.of("USER")),
//                new Player("mvestro",passwordEncoder.encode("haribo"), Role.USER, List.of(card, card1))
////                new Player().setUsername("admin").setPassword(passwordEncoder.encode("admintest")).setRoles(List.of("USER", "ADMIN"))
//        ).forEach(accountRepository::save);
    }

}
