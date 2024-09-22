package com.orangescout.Orange.Scout.controller;

import com.orangescout.Orange.Scout.exception.EditTeamException;
import com.orangescout.Orange.Scout.exception.UserNotFoundException;
import com.orangescout.Orange.Scout.model.Team;
import com.orangescout.Orange.Scout.repository.TeamRepository;
import com.orangescout.Orange.Scout.repository.UserRepository;
import com.orangescout.Orange.Scout.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams (){
        List<Team> teams = teamService.getAllTeams();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Team> createTeam (@RequestBody Team team){
        Team newTeam = teamService.saveTeam(team);
        return new ResponseEntity<>(newTeam, HttpStatus.CREATED);
    }

    @PutMapping("/team/{id}")
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

    @DeleteMapping("/team/{id}")
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
