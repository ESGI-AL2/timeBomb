package fr.esgi.timebomb.controller;

import fr.esgi.timebomb.authentification.Login;
//import fr.esgi.timebomb.authentification.Register;
import fr.esgi.timebomb.authentification.Register;
import fr.esgi.timebomb.dto.LoginDTO;
import fr.esgi.timebomb.dto.RegisterDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final Register register;
    private final Login login;

    public AuthController(Register register, Login login) {
        this.register = register;
        this.login = login;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO){
        URI uri = register.execute(registerDTO);
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        HttpHeaders httpHeaders = login.execute(loginDTO);
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }
}
