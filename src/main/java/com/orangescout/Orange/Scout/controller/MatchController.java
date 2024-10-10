package com.orangescout.Orange.Scout.controller;

import com.orangescout.Orange.Scout.dto.MatchDTO;
import com.orangescout.Orange.Scout.dto.MatchRequest;
import com.orangescout.Orange.Scout.model.Match;
import com.orangescout.Orange.Scout.model.User;
import com.orangescout.Orange.Scout.security.MyUserDetails;
import com.orangescout.Orange.Scout.service.MatchService;
import com.orangescout.Orange.Scout.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping
    public ResponseEntity<List<Match>> getAllMatches(){
        List<Match> matches = matchService.getAllMatches();
        return new ResponseEntity<>(matches, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MatchDTO> createMatch(@RequestBody MatchDTO matchDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        MyUserDetails userDetails = (MyUserDetails) userDetailsService.loadUserByUsername(email);
        User user = userDetails.getUser();
        Match match = matchService.convertToEntity(matchDTO);

        // Salva a entidade no banco de dados
        match.setUser(user);
        Match savedMatch = matchService.save(match);

        // Converte a entidade salva de volta para MatchDTO
        MatchDTO savedMatchDTO = matchService.convertToDTO(savedMatch);

        // Retorna a resposta com o MatchDTO criado
        return new ResponseEntity<>(savedMatchDTO, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MatchDTO>> getMatchesByUserId(@PathVariable Long userId) {
        List<MatchDTO> matches = matchService.getMatchesByUserId(userId);
        return ResponseEntity.ok(matches);
    }
}
