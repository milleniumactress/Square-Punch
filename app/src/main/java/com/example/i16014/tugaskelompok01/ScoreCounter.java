package com.example.i16014.tugaskelompok01;

/**
 * Created by user on 27/09/2018.
 */

public class ScoreCounter {

    protected int recentScore;

    public ScoreCounter(){
        this.recentScore = 0;
    }

    public void addScore(){
        this.recentScore++;
    }

}
