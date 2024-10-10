package com.orangescout.Orange.Scout.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import net.minidev.json.annotate.JsonIgnore;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_player;

    @ManyToOne
    @JoinColumn(name = "id_team")
    @JsonBackReference
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

    @JsonProperty("teamName") // Isso vai aparecer no JSON como "teamName"
    public String getTeamName() {
        return this.team != null ? this.team.getName_team() : null;
    }
}
