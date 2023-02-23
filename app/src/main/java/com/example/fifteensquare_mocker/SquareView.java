package com.example.fifteensquare_mocker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SquareView extends SurfaceView {
    public SquareModel model;
    public ArrayList<SquareModel> squares = new ArrayList<SquareModel>();
    private int size = model.SQUARE_SIZE;
    public Paint squarePaint = new Paint();
    private Paint squareText = new Paint();
    private int fontSize = 115;
    Random r = new Random();
    public SquareModel emptySquare;
    int emptySquareIndex;

    public SquareView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        squares.add(new SquareModel(10,10));
        squares.add(new SquareModel(2*10+size,10));
        squares.add(new SquareModel(3*10+2*size,10));
        squares.add(new SquareModel(4*10+3*size,10));

        squares.add(new SquareModel(10,2*10+size));
        squares.add(new SquareModel(2*10+size,2*10+size));
        squares.add(new SquareModel(3*10+2*size,2*10+size));
        squares.add(new SquareModel(4*10+3*size,2*10+size));

        squares.add(new SquareModel(10,3*10+2*size));
        squares.add(new SquareModel(2*10+size,3*10+2*size));
        squares.add(new SquareModel(3*10+2*size,3*10+2*size));
        squares.add(new SquareModel(4*10+3*size,3*10+2*size));

        squares.add(new SquareModel(10,4*10+3*size));
        squares.add(new SquareModel(2*10+size,4*10+3*size));
        squares.add(new SquareModel(3*10+2*size,4*10+3*size));

        emptySquare = new SquareModel(4*10+3*size,4*10+3*size);
        squares.add(emptySquare); //the 16th empty square
        emptySquareIndex = 15;

        setNumbers(squares);

        squarePaint.setColor(Color.WHITE);
        squarePaint.setStyle(Paint.Style.FILL);
        squareText.setColor(Color.DKGRAY);
        squareText.setTextSize(fontSize);
    } //ctor

    public void drawSquare(Canvas canvas, int x, int y, int number) {
        canvas.drawRect(x, y, x + size, y + size, squarePaint);
        canvas.drawText(String.valueOf(number), x + 10, y + fontSize, squareText);
    } //drawSquare

    //assign each square a number with random!
    public void setNumbers(ArrayList<SquareModel> s) {
        Set<Integer> set = new HashSet<>();
        //create a list of unique numbers and make sure they don't copy each other
        //using a hashset (my dad's recommendation) because they all represent unique objects
        for(int i = 0; i < s.size() -1; ++i) {
            int x;
            do {
                x = r.nextInt(15) + 1;
            }
            while(set.contains(x));
            set.add(x);

            s.get(i).number = x;
        }
        s.get(15).number = 16; //emptySquare is always 16
    }//setNumbers

    public void onDraw(Canvas canvas) {
            for (int i = 0; i < squares.size(); ++i) {
                if(squares.get(i).number != 16) {
                    drawSquare(canvas, (int) squares.get(i).x, (int) squares.get(i).y, squares.get(i).number);
                }
            }
    } //onDraw

    public void reset() {
        //resets the x and y positions, and randomizes the numbers again
        squares.get(0).setCoords(10, 10);
        squares.get(1).setCoords(2*10+size,10);
        squares.get(2).setCoords(3*10+2*size,10);
        squares.get(3).setCoords(4*10+3*size,10);

        squares.get(4).setCoords(10,2*10+size);
        squares.get(5).setCoords(2*10+size,2*10+size);
        squares.get(6).setCoords(3*10+2*size,2*10+size);
        squares.get(7).setCoords(4*10+3*size,2*10+size);

        squares.get(8).setCoords(10,3*10+2*size);
        squares.get(9).setCoords(2*10+size,3*10+2*size);
        squares.get(10).setCoords(3*10+2*size,3*10+2*size);
        squares.get(11).setCoords(4*10+3*size,3*10+2*size);

        squares.get(12).setCoords(10,4*10+3*size);
        squares.get(13).setCoords(2*10+size,4*10+3*size);
        squares.get(14).setCoords(3*10+2*size,4*10+3*size);
        squares.get(15).setCoords(4*10+3*size,4*10+3*size);
        emptySquare = squares.get(15);
        emptySquareIndex = 15;

        setNumbers(squares);
    }//reset

    public SquareModel getSquareModel() {
        return model;
    }
}
