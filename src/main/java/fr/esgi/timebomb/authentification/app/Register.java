package fr.esgi.timebomb.authentification.app;


import fr.esgi.timebomb.authentification.AddAccount;
import fr.esgi.timebomb.authentification.app.dto.RegisterDTO;
import fr.esgi.timebomb.authentification.domain.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service
public class Register {

    private final AddAccount addAccount;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public Register(AddAccount addAccount, PasswordEncoder passwordEncoder) {
        this.addAccount = addAccount;
        this.passwordEncoder = passwordEncoder;
    }

    public URI execute(RegisterDTO registerDTO) {
        Account account = new Account(registerDTO.getUsername(), passwordEncoder.encode(registerDTO.getPassword()), registerDTO.getRole());
        String id = addAccount.execute(account).getId();
        return ServletUriComponentsBuilder.fromPath("/api/users").path("/{id}").buildAndExpand(id).toUri();
    }
}
