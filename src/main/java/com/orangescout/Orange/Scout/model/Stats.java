package com.orangescout.Orange.Scout.model;

import jakarta.persistence.*;

@Entity
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

    public int getFoul() {
        return foul;
    }

    public void setFoul(int foul) {
        this.foul = foul;
    }

    public int getOffensive_rebound() {
        return offensive_rebound;
    }

    public void setOffensive_rebound(int offensive_rebound) {
        this.offensive_rebound = offensive_rebound;
    }

    public int getDefensive_rebound() {
        return defensive_rebound;
    }

    public void setDefensive_rebound(int defensive_rebound) {
        this.defensive_rebound = defensive_rebound;
    }

    public int getAssist() {
        return assist;
    }

    public void setAssist(int assist) {
        this.assist = assist;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getTurnover() {
        return turnover;
    }

    public void setTurnover(int turnover) {
        this.turnover = turnover;
    }

    public int getSteal() {
        return steal;
    }

    public void setSteal(int steal) {
        this.steal = steal;
    }

    public int getMissed_one_pointer() {
        return missed_one_pointer;
    }

    public void setMissed_one_pointer(int missed_one_pointer) {
        this.missed_one_pointer = missed_one_pointer;
    }

    public int getMissed_two_pointer() {
        return missed_two_pointer;
    }

    public void setMissed_two_pointer(int missed_two_pointer) {
        this.missed_two_pointer = missed_two_pointer;
    }

    public int getMissed_three_pointer() {
        return missed_three_pointer;
    }

    public void setMissed_three_pointer(int missed_three_pointer) {
        this.missed_three_pointer = missed_three_pointer;
    }

    public int getOne_pointer() {
        return one_pointer;
    }

    public void setOne_pointer(int one_pointer) {
        this.one_pointer = one_pointer;
    }

    public int getTwo_pointer() {
        return two_pointer;
    }

    public void setTwo_pointer(int two_pointer) {
        this.two_pointer = two_pointer;
    }

    public int getThree_pointer() {
        return three_pointer;
    }

    public void setThree_pointer(int three_pointer) {
        this.three_pointer = three_pointer;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Long getId_stats() {
        return id_stats;
    }

    public void setId_stats(Long id_stats) {
        this.id_stats = id_stats;
    }
}
