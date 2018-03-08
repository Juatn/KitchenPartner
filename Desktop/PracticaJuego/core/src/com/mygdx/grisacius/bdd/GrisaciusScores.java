package com.mygdx.grisacius.bdd;

import java.sql.Timestamp;

public class GrisaciusScores {
    private int score;
    private Timestamp gameStartDate;
    private Timestamp gameEndDate;

    public GrisaciusScores(Timestamp startDate, int score, Timestamp endDate) {
        this.score = score;
        this.gameStartDate = startDate;
        this.gameEndDate = endDate;//
    }

    public int getScore() {
        return score;
    }

    public Timestamp getGameStartDate() {
        return gameStartDate;
    }

    public Timestamp getGameEndDate() {
        return gameEndDate;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setGameEndDate(Timestamp endDate) {
        this.gameEndDate = endDate;
    }

    @Override
    public String toString() {
        return
                "Score => " + score +"\n"+
                "Fecha inicio =>  " + gameStartDate +"\n"+
                "Fecha fin= =>  " + gameEndDate+"\n";

    }
}
