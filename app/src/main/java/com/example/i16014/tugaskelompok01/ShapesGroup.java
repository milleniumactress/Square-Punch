package com.example.i16014.tugaskelompok01;

import android.graphics.Rect;

import java.util.ArrayList;
import java.util.Random;

public class ShapesGroup {

    protected ArrayList<Rect> shapesArray;

    public ShapesGroup(){
        Random rand = new Random();
        this.shapesArray = new ArrayList<>();
    }

    public void generateShape(double x, double y){
        Random rand = new Random();
        int limit = 10;
        for(int i=0;i<limit;i++) {
            int xCoor = rand.nextInt((int) x);
            int yCoor = rand.nextInt((int) y);
            Rect rect = new Rect(xCoor, yCoor, xCoor+200, yCoor+200);
            this.shapesArray.add(rect);
        }

        for(int i=0;i<limit-1;i++){
            Rect temp = this.shapesArray.get(i);
            for(int j=i;j<limit;j++){
                temp.intersect(this.shapesArray.get(j));
            }
        }
    }

    public void clearShapes(){
        this.shapesArray.clear();
    }

    public int getNoOfShapes(){
        return this.shapesArray.size();
    }

}
