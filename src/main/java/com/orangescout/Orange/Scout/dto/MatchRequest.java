package com.orangescout.Orange.Scout.dto;

import com.orangescout.Orange.Scout.model.User;

import java.time.LocalDate;

public class MatchRequest {

    private Long teamOneId;
    private Long teamTwoId;
    private int teamOneScore;
    private int teamTwoScore;
    private LocalDate dateMatch;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getTeamOneId() {
        return teamOneId;
    }

    public void setTeamOneId(Long teamOneId) {
        this.teamOneId = teamOneId;
    }

    public Long getTeamTwoId() {
        return teamTwoId;
    }

    public void setTeamTwoId(Long teamTwoId) {
        this.teamTwoId = teamTwoId;
    }

    public int getTeamOneScore() {
        return teamOneScore;
    }

    public void setTeamOneScore(int teamOneScore) {
        this.teamOneScore = teamOneScore;
    }

    public int getTeamTwoScore() {
        return teamTwoScore;
    }

    public void setTeamTwoScore(int teamTwoScore) {
        this.teamTwoScore = teamTwoScore;
    }

    public LocalDate getDateMatch() {
        return dateMatch;
    }

    public void setDateMatch(LocalDate dateMatch) {
        this.dateMatch = dateMatch;
    }
}
