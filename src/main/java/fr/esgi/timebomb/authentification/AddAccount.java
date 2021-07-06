package fr.esgi.timebomb.authentification;


import fr.esgi.timebomb.dao.AccountRepository;
import fr.esgi.timebomb.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddAccount {

    private final AccountRepository accountDao;

    @Autowired
    public AddAccount(AccountRepository accountDao) {
        this.accountDao = accountDao;
    }

    public Player execute(Player account) {
        return accountDao.save(account);
    }
}
