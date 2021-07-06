package fr.esgi.timebomb.controller;

import fr.esgi.timebomb.dao.AccountRepository;
import fr.esgi.timebomb.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private AccountRepository playerDao;

    @GetMapping
    public ResponseEntity<List<Player>> listPlayer(){
        return ok(playerDao.findAll());
    }
}
