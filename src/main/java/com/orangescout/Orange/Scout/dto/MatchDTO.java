package com.orangescout.Orange.Scout.dto;

import java.time.LocalDate;

public class MatchDTO {
        private Long matchId;
        private int teamOneScore;
        private int teamTwoScore;
        private LocalDate matchDate;
        private TeamDTO teamOne;
        private TeamDTO teamTwo;

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
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

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDate matchDate) {
        this.matchDate = matchDate;
    }

    public TeamDTO getTeamOne() {
        return teamOne;
    }

    public void setTeamOne(TeamDTO teamOne) {
        this.teamOne = teamOne;
    }

    public TeamDTO getTeamTwo() {
        return teamTwo;
    }

    public void setTeamTwo(TeamDTO teamTwo) {
        this.teamTwo = teamTwo;
    }
}
