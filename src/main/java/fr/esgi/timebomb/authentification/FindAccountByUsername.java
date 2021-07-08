package fr.esgi.timebomb.authentification;


import fr.esgi.timebomb.domain.Player;
import fr.esgi.timebomb.repository.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class FindAccountByUsername {

    private final PlayerRepository accountDao;

    public FindAccountByUsername(PlayerRepository accountDao) {
        this.accountDao = accountDao;
    }

    public Player execute(String username){
        return accountDao.findPlayerByUsername(username)
                .orElseThrow(() -> new AccountWithUsernameNotFoundException(username));
    }
}
