package com.orangescout.Orange.Scout.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_match;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_one_id", nullable = false)
    private Team teamOne;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_two_id", nullable = false)
    private Team teamTwo;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    private int team_one_score;
    private int team_two_score;
    private LocalDate date_match;

    public Long getId_match() {
        return id_match;
    }

    public void setId_match(Long id_match) {
        this.id_match = id_match;
    }

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

    public int getTeam_one_score() {
        return team_one_score;
    }

    public void setTeam_one_score(int team_one_score) {
        this.team_one_score = team_one_score;
    }

    public int getTeam_two_score() {
        return team_two_score;
    }

    public void setTeam_two_score(int team_two_score) {
        this.team_two_score = team_two_score;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
