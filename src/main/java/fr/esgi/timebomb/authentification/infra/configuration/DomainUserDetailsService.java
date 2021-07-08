package fr.esgi.timebomb.authentification.infra.configuration;

import fr.esgi.timebomb.authentification.domain.Account;
import fr.esgi.timebomb.authentification.domain.AccountRepository;
import fr.esgi.timebomb.repository.PlayerRepository;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class DomainUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public DomainUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findPlayerByUsername(username)
                .orElseThrow(() -> new AuthenticationServiceException("username " + username + " not found"));

        return User.builder()
                .username(username)
                .password(account.getPassword())
                .roles(account.getRole().name())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
