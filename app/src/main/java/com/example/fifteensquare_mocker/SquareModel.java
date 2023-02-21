package com.example.fifteensquare_mocker;

import java.util.ArrayList;
import java.util.Random;

public class SquareModel {
    public static final int SQUARE_SIZE = 250;
    public float x = 10;
    public float y = 10;
    public int number = 1;

    public SquareModel(float x, float y) {
        this.x = x;
        this.y = y;
    } //ctor

    public void setCoords(float x, float y) {
        this.x = x;
        this.y = y;
    }//setCoords

}
