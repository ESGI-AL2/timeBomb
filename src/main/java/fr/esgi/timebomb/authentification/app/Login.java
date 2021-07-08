package fr.esgi.timebomb.authentification.app;

import fr.esgi.timebomb.authentification.FindAccountByUsername;
import fr.esgi.timebomb.authentification.app.dto.LoginDTO;
import fr.esgi.timebomb.authentification.infra.configuration.TokenProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class Login {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final FindAccountByUsername findAccountByUsername;

    public Login(TokenProvider tokenProvider,
                 AuthenticationManagerBuilder authenticationManagerBuilder,
                 FindAccountByUsername findAccountByUsername) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.findAccountByUsername = findAccountByUsername;
    }

    public HttpHeaders execute(LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        String token = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        return httpHeaders;
    }
}
