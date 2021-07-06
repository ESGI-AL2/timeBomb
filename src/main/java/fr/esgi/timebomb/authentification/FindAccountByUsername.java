package fr.esgi.timebomb.authentification;


import fr.esgi.timebomb.dao.AccountRepository;
import fr.esgi.timebomb.domain.Player;
import org.springframework.stereotype.Service;

@Service
public class FindAccountByUsername {

    private final AccountRepository accountDao;

    public FindAccountByUsername(AccountRepository accountDao) {
        this.accountDao = accountDao;
    }

    public Player execute(String username){
        return accountDao.findPlayerByUsername(username)
                .orElseThrow(() -> new AccountWithUsernameNotFoundException(username));
    }
}
