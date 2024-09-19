package com.orangescout.Orange.Scout.model;

import jakarta.persistence.*;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_player;

    @ManyToOne
    @JoinColumn(name = "id_team")
    private Team team;

    public Long getId_player() {
        return id_player;
    }

    public void setId_player(Long id_player) {
        this.id_player = id_player;
    }

    private String name_player;
    private int jersey_player;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getName_player() {
        return name_player;
    }

    public void setName_player(String name_player) {
        this.name_player = name_player;
    }

    public int getJersey_player() {
        return jersey_player;
    }

    public void setJersey_player(int jersey_player) {
        this.jersey_player = jersey_player;
    }
}
