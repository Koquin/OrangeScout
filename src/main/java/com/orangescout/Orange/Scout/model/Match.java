package com.orangescout.Orange.Scout.model;

import jakarta.persistence.*;

import java.time.LocalDate;

public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_match;

    @OneToOne
    @JoinColumn(name = "id_team")
    private Team teamOne;

    @OneToOne
    @JoinColumn(name = "id_team")
    private Team teamTwo;

    private LocalDate date_match;

    public Team getTeamOne() {
        return teamOne;
    }

    public void setTeamOne(Team teamOne) {
        this.teamOne = teamOne;
    }

    public Team getTeamTwo() {
        return teamTwo;
    }

    public void setTeamTwo(Team teamTwo) {
        this.teamTwo = teamTwo;
    }

    public LocalDate getDate_match() {
        return date_match;
    }

    public void setDate_match(LocalDate date_match) {
        this.date_match = date_match;
    }
}
