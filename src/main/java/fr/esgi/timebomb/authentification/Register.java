package fr.esgi.timebomb.authentification;


import fr.esgi.timebomb.domain.Player;
import fr.esgi.timebomb.dto.RegisterDTO;
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
        Player account = new Player(registerDTO.getUsername(), passwordEncoder.encode(registerDTO.getPassword()), registerDTO.getRole());
        long id = addAccount.execute(account).getId();
        return ServletUriComponentsBuilder.fromPath("/api/users").path("/{id}").buildAndExpand(id).toUri();
    }
}
