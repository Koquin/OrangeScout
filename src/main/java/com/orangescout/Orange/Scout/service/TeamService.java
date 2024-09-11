package com.orangescout.Orange.Scout.service;

import com.orangescout.Orange.Scout.exception.TeamNotFoundException;
import com.orangescout.Orange.Scout.model.Team;
import com.orangescout.Orange.Scout.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public List<Team> getAllTeams(){
        return teamRepository.findAll();
    }

    public Team getTeamById(Long id){
        return teamRepository.findById(id).orElseThrow(
                () -> new TeamNotFoundException("Team not found")
        );
    }

    public void deleteTeam (Long id){
        if (!teamRepository.existsById(id)){
            throw new TeamNotFoundException("Team not found");
        }
        teamRepository.deleteById(id);
    }

    public Team saveTeam (Team team){
        return teamRepository.save(team);
    }

}
