package com.orangescout.Orange.Scout.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_team;

    private String name_team;
    private String team_logo_path;
    private String abbr_team;

    @OneToMany(mappedBy = "team")
    private List<Player> players;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @OneToMany(mappedBy = "teamOne")
    private List<Match> matches;

    @OneToMany(mappedBy = "teamTwo")
    private List<Match> awayMatches;

    public Long getId_team() {
        return id_team;
    }

    public void setId_team(Long id_team) {
        this.id_team = id_team;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public String getName_team() {
        return name_team;
    }

    public void setName_team(String name_team) {
        this.name_team = name_team;
    }

    public String getTeam_logo_path() {
        return team_logo_path;
    }

    public void setTeam_logo_path(String team_logo_path) {
        this.team_logo_path = team_logo_path;
    }

    public String getAbbr_team() {
        return abbr_team;
    }

    public void setAbbr_team(String abbr_team) {
        this.abbr_team = abbr_team;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public List<Match> getAwayMatches() {
        return awayMatches;
    }

    public void setAwayMatches(List<Match> awayMatches) {
        this.awayMatches = awayMatches;
    }
}
