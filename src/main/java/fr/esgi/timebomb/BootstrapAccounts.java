package fr.esgi.timebomb;

import fr.esgi.timebomb.domain.Card;
import fr.esgi.timebomb.domain.Player;
import fr.esgi.timebomb.dao.AccountRepository;
import fr.esgi.timebomb.domain.Role;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.List.*;

@Component
class BootstrapAccounts {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    BootstrapAccounts(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

//    @EventListener
//    void onStartup(ApplicationReadyEvent event) {
//        Card card = new Card().setValue(Card.Value.BOMB);
//        Card card1 = new Card().setValue(Card.Value.COLOR);
//        of(
////                new Player().setUsername("mvestro").setPassword(passwordEncoder.encode("haribo")).setRoles(List.of("USER")),
//                new Player("mvestro",passwordEncoder.encode("haribo"), Role.USER, List.of(card, card1))
////                new Player().setUsername("admin").setPassword(passwordEncoder.encode("admintest")).setRoles(List.of("USER", "ADMIN"))
//        ).forEach(accountRepository::save);
//    }

}
