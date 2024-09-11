package com.orangescout.Orange.Scout.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_team;

    private String name_team;
    private String team_logo_path;
    private String abbr_team;

    @OneToMany(mappedBy = "team")
    private List<Player> players;

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
}
