package com.orangescout.Orange.Scout.dtos;

public class MatchDTO {
        private Long matchId;
        private int teamOneScore;
        private int teamTwoScore;
        private String matchDate;
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

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
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
