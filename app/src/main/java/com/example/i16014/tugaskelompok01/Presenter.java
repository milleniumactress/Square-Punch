package com.example.i16014.tugaskelompok01;

import android.graphics.drawable.shapes.Shape;

public class Presenter {

    protected ScoreCounter score;
    protected ShapesGroup shapeArr;

    public Presenter(){
        this.score = new ScoreCounter();
        this.shapeArr = new ShapesGroup();
    }

    public void addScore(){
        this.score.addScore();
    }

    public int getScore(){
        return this.score.recentScore;
    }

    public void generateShape(int x, int y){
        this.shapeArr.generateShape(x,y);
    }

}
