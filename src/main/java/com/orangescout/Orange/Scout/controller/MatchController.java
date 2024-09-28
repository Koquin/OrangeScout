package com.orangescout.Orange.Scout.controller;

import com.orangescout.Orange.Scout.dto.MatchDTO;
import com.orangescout.Orange.Scout.model.Match;
import com.orangescout.Orange.Scout.service.MatchService;
import com.orangescout.Orange.Scout.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Match>> getAllMatches(){
        List<Match> matches = matchService.getAllMatches();
        return new ResponseEntity<>(matches, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Match> createMatch(@RequestBody Match match){
        Match newMatch = matchService.saveMatch(match);
        return new ResponseEntity<>(newMatch, HttpStatus.CREATED);
    }

    @GetMapping("/match/user/{userId}")
    public ResponseEntity<List<MatchDTO>> getMatchesByUserId(@PathVariable Long userId) {
        List<MatchDTO> matches = matchService.getMatchesByUserId(userId);
        return ResponseEntity.ok(matches);
    }
}
