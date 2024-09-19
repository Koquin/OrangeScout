package com.orangescout.Orange.Scout.controller;

import com.orangescout.Orange.Scout.exception.EditPlayerException;
import com.orangescout.Orange.Scout.model.Player;
import com.orangescout.Orange.Scout.repository.PlayerRepository;
import com.orangescout.Orange.Scout.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers(){
        List<Player> players = playerService.getAllPlayers();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody Player player){
        Player newPlayer = playerService.savePlayer(player);
        return new ResponseEntity<>(newPlayer, HttpStatus.CREATED);
    }

    @PutMapping("/player/{id}")
    public ResponseEntity<Player> editPlayer (@RequestBody Player player, @PathVariable Long id){
        try {
            Player editedPlayer = playerService.getPlayerById(id);
            editedPlayer.setJersey_player(player.getJersey_player());
            editedPlayer.setName_player(player.getName_player());
            playerService.savePlayer(editedPlayer);
            return new ResponseEntity<>(editedPlayer, HttpStatus.ACCEPTED);
        } catch (EditPlayerException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/player/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id){
        if (!playerRepository.existsById(id)){
            return ResponseEntity.noContent().build();
        } else {
            playerService.deletePlayerById(id);
            return ResponseEntity.notFound().build();
        }
    }

}
