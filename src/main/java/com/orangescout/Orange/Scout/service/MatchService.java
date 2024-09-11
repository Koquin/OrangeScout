package com.orangescout.Orange.Scout.service;

import com.orangescout.Orange.Scout.exception.MatchNotFoundException;
import com.orangescout.Orange.Scout.model.Match;
import com.orangescout.Orange.Scout.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

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
}
