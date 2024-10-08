package com.orangescout.Orange.Scout.controller;

import com.orangescout.Orange.Scout.exception.EditTeamException;
import com.orangescout.Orange.Scout.exception.UserNotFoundException;
import com.orangescout.Orange.Scout.model.Team;
import com.orangescout.Orange.Scout.model.User;
import com.orangescout.Orange.Scout.repository.TeamRepository;
import com.orangescout.Orange.Scout.repository.UserRepository;
import com.orangescout.Orange.Scout.security.MyUserDetails;
import com.orangescout.Orange.Scout.security.UserDetailsServiceImpl;
import com.orangescout.Orange.Scout.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams (){
        List<Team> teams = teamService.getAllTeams();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long id){
        Team teamGet = teamService.getTeamById(id);
        return new ResponseEntity<>(teamGet, HttpStatus.OK);
    }

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping
    public void createTeam(@RequestBody Team team) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        MyUserDetails userDetails = (MyUserDetails) userDetailsService.loadUserByUsername(email);
        User user = userDetails.getUser();

        team.setUser(user);

        teamRepository.save(team);
    }


    @PutMapping("{id}")
    public ResponseEntity<Team> editTeam(@RequestBody Team team, @PathVariable Long id){
        try {
            Team editedTeam = teamService.getTeamById(id);
            editedTeam.setName_team(team.getName_team());
            editedTeam.setAbbr_team(team.getAbbr_team());
            editedTeam.setTeam_logo_path(team.getTeam_logo_path());
            teamService.saveTeam(editedTeam);
            return new ResponseEntity<>(editedTeam, HttpStatus.ACCEPTED);
        } catch (EditTeamException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id){
        if (teamRepository.existsById(id)){
            teamService.deleteTeamById(id);
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

}
