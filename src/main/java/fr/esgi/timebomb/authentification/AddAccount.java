package fr.esgi.timebomb.authentification;

import fr.esgi.timebomb.domain.Player;
import fr.esgi.timebomb.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddAccount {

    private final PlayerRepository accountDao;

    @Autowired
    public AddAccount(PlayerRepository accountDao) {
        this.accountDao = accountDao;
    }

    public Player execute(Player account) {
        return accountDao.save(account);
    }
}
