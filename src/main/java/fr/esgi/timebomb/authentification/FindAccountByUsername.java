package fr.esgi.timebomb.authentification;


import fr.esgi.timebomb.authentification.domain.Account;
import fr.esgi.timebomb.authentification.domain.AccountRepository;
import fr.esgi.timebomb.exceptions.AccountWithUsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FindAccountByUsername {

    private final AccountRepository accountRepository;

    public FindAccountByUsername(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account execute(String username){
        return accountRepository.findPlayerByUsername(username)
                .orElseThrow(() -> new AccountWithUsernameNotFoundException(username));
    }
}
