package com.orangescout.Orange.Scout.service;

import com.orangescout.Orange.Scout.exception.PlayerNotFoundException;
import com.orangescout.Orange.Scout.model.Player;
import com.orangescout.Orange.Scout.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public Player getPlayerById(Long id){
        return playerRepository.findById(id).orElseThrow(
                () -> new PlayerNotFoundException("Player not Found")
        );
    }

    public List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }

    public Player savePlayer (Player player){
        return playerRepository.save(player);
    }

    public void deletePlayerById(Long id){
        if (!playerRepository.existsById(id)){
            throw new PlayerNotFoundException("Player not found");
        }
        playerRepository.deleteById(id);
    }




}
