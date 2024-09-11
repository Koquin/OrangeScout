package com.orangescout.Orange.Scout.model;

import jakarta.persistence.*;

public class Stats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_stats;

    @OneToOne
    @JoinColumn(name = "id_player")
    private Player player;

    @OneToOne
    @JoinColumn(name = "id_match")
    private Match match;

    private int three_pointer;
    private int two_pointer;
    private int one_pointer;
    private int missed_three_pointer;
    private int missed_two_pointer;
    private int missed_one_pointer;
    private int steal;
    private int turnover;
    private int block;
    private int assist;
    private int offensive_rebound;
    private int defensive_rebound;
    private int foul;


}
