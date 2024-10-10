package com.orangescout.Orange.Scout.service;

import com.orangescout.Orange.Scout.dto.MatchDTO;
import com.orangescout.Orange.Scout.dto.MatchRequest;
import com.orangescout.Orange.Scout.dto.TeamDTO;
import com.orangescout.Orange.Scout.exception.MatchNotFoundException;
import com.orangescout.Orange.Scout.model.Match;
import com.orangescout.Orange.Scout.model.Team;
import com.orangescout.Orange.Scout.model.User;
import com.orangescout.Orange.Scout.repository.MatchRepository;
import com.orangescout.Orange.Scout.repository.TeamRepository;
import com.orangescout.Orange.Scout.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;


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

    public Match convertToEntity(MatchDTO matchDTO) {
        Match match = new Match();
        match.setTeam_one_score(matchDTO.getTeamOneScore());
        match.setTeam_two_score(matchDTO.getTeamTwoScore());
        match.setDate_match(matchDTO.getMatchDate());

        Team teamOne = new Team();
        teamOne.setId_team(matchDTO.getTeamOne().getTeamId());
        match.setTeamOne(teamOne);

        Team teamTwo = new Team();
        teamTwo.setId_team(matchDTO.getTeamTwo().getTeamId());
        match.setTeamTwo(teamTwo);

        // Defina o usuário associado se necessário (assumindo que você tem a autenticação no contexto)
        // match.setUser(user);

        return match;
    }

    public MatchDTO convertToDTO(Match match) {
        MatchDTO matchDTO = new MatchDTO();
        matchDTO.setMatchId(match.getId_match());
        matchDTO.setTeamOneScore(match.getTeam_one_score());
        matchDTO.setTeamTwoScore(match.getTeam_two_score());
        matchDTO.setMatchDate(match.getDate_match());

        // Converta os times para DTO
        TeamDTO teamOneDTO = new TeamDTO();
        teamOneDTO.setTeamId(match.getTeamOne().getId_team());
        teamOneDTO.setTeamName(match.getTeamOne().getName_team());
        teamOneDTO.setLogoPath(match.getTeamOne().getTeam_logo_path());
        teamOneDTO.setAbbreviation(match.getTeamOne().getAbbr_team());
        matchDTO.setTeamOne(teamOneDTO);

        TeamDTO teamTwoDTO = new TeamDTO();
        teamTwoDTO.setTeamId(match.getTeamTwo().getId_team());
        teamTwoDTO.setTeamName(match.getTeamTwo().getName_team());
        teamTwoDTO.setLogoPath(match.getTeamTwo().getTeam_logo_path());
        teamTwoDTO.setAbbreviation(match.getTeamTwo().getAbbr_team());
        matchDTO.setTeamTwo(teamTwoDTO);

        return matchDTO;
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

    public Match save(Match match) {

        Team teamOne = match.getTeamOne();
        Team teamTwo = match.getTeamTwo();

        if (teamOne == null || teamTwo == null) {
            throw new IllegalArgumentException("Os times não podem ser nulos");
        }

        if (teamRepository.findById(teamOne.getId_team()).isEmpty() ||
                teamRepository.findById(teamTwo.getId_team()).isEmpty()) {
            throw new IllegalArgumentException("Um ou ambos os times não existem");
        }

        return matchRepository.save(match);
    }
}
