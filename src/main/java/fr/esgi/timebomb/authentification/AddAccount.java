package fr.esgi.timebomb.authentification;

import fr.esgi.timebomb.authentification.domain.Account;
import fr.esgi.timebomb.authentification.domain.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddAccount {

    private final AccountRepository accountRepository;

    @Autowired
    public AddAccount(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account execute(Account account) {
        return accountRepository.save(account);
    }
}
