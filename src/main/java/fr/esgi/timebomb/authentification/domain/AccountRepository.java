package fr.esgi.timebomb.authentification.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, String> {

    Optional<Account> findPlayerByUsername(String username);

}
