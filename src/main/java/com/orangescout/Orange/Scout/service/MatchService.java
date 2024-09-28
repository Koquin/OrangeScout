package com.orangescout.Orange.Scout.service;

import com.orangescout.Orange.Scout.dto.MatchDTO;
import com.orangescout.Orange.Scout.dto.TeamDTO;
import com.orangescout.Orange.Scout.exception.MatchNotFoundException;
import com.orangescout.Orange.Scout.model.Match;
import com.orangescout.Orange.Scout.model.User;
import com.orangescout.Orange.Scout.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private UserService userService;

    public List<Match> getAllMatches(){
        return matchRepository.findAll();
    }

    public Match getMatchById(Long id){
        return matchRepository.findById(id).orElseThrow(
                () -> new MatchNotFoundException("Match not found")
        );
    }

    public Match saveMatch(Match match){
        return matchRepository.save(match);
    }

    public void deleteMatchById(Long id){
        if (!matchRepository.existsById(id)){
            throw new MatchNotFoundException(("Match not found"));
        }
        matchRepository.deleteById(id);
    }

    public List<Match> getAllMatchesByUser(User user){
        return matchRepository.findByUser(user);
    }

    public List<MatchDTO> getMatchesByUserId(Long id){
        List<Match> matches = matchRepository.findByUser(userService.getUserById(id));
        List<MatchDTO> matchDTOS = new ArrayList<>();

        for (Match match : matches){
            MatchDTO dto = new MatchDTO();
            dto.setMatchDate(match.getDate_match());
            dto.setMatchId(match.getId_match());
            dto.setTeamOneScore(match.getTeam_one_score());
            dto.setTeamTwoScore(match.getTeam_two_score());

            TeamDTO tDto = new TeamDTO();
            tDto.setAbbreviation(match.getTeamOne().getAbbr_team());
            tDto.setLogoPath(match.getTeamOne().getTeam_logo_path());

            TeamDTO tDto2 = new TeamDTO();
            tDto2.setLogoPath(match.getTeamTwo().getTeam_logo_path());
            tDto2.setAbbreviation(match.getTeamTwo().getAbbr_team());

            dto.setTeamOne(tDto);
            dto.setTeamTwo(tDto2);

            matchDTOS.add(dto);
        }

        return matchDTOS;
    }
}
