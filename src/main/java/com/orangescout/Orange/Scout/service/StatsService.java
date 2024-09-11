package com.orangescout.Orange.Scout.service;

import com.orangescout.Orange.Scout.exception.StatsNotFoundException;
import com.orangescout.Orange.Scout.model.Stats;
import com.orangescout.Orange.Scout.repository.StatsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StatsService {

    @Autowired
    private StatsRepository statsRepository;

    public Stats getStatsById(Long id){
        return statsRepository.findById(id).orElseThrow(
                () -> new StatsNotFoundException("Stats not found")
        );
    }

    public List<Stats> getAllStats(){
        return statsRepository.findAll();
    }

    public Stats saveStats(Stats stats){
        return statsRepository.save(stats);
    }

    public void deleteById(Long id){
        if (!statsRepository.existsById(id)){
            throw new StatsNotFoundException("Stats not found");
        }
        statsRepository.deleteById(id);
    }





}
