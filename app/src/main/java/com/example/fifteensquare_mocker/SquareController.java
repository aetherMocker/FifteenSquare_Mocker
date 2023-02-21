package com.example.fifteensquare_mocker;

import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class SquareController implements View.OnTouchListener, View.OnClickListener{
    private SquareView squareView;
    private SquareModel squareModel;
    SquareModel holder;
    float holderX;
    float holderY;
    int holderIndex;
    float pressX;
    float pressY;
    float origX;
    float origY;
    boolean movedHoriz;

    public SquareController(SquareView v){
        squareView = v;
        squareModel = squareView.getSquareModel();
    }//ctor

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //loop through the 15 model objects which me.getX and Y it is over
        //then change that one's location and no one else's

        if (motionEvent.getActionMasked() == 0) { //when the button is pressed
            for(int i = 0; i < squareView.squares.size(); ++i) {

                //find which model object is the the moving square
                if (motionEvent.getX() <= squareView.squares.get(i).x + squareModel.SQUARE_SIZE
                        && motionEvent.getY() <= squareView.squares.get(i).y + squareModel.SQUARE_SIZE
                        && motionEvent.getX() >= squareView.squares.get(i).x
                        && motionEvent.getY() >= squareView.squares.get(i).y) {

                    pressX = motionEvent.getX();
                    pressY = motionEvent.getY();
                    origX = squareView.squares.get(i).x;
                    origY = squareView.squares.get(i).y;


                    holder = squareView.squares.get(i); //what object is moving
                    holderX = holder.x; //the x and y
                    holderY = holder.y;
                    holderIndex = i; //the position in the array of the moving object

                    squareView.invalidate();
                }
            }
        }
        else if(motionEvent.getActionMasked() == 2) { //square is being dragged

            //find the difference of x and y changed after dragging
            float xChanged = Math.abs(pressX - motionEvent.getX());
            float yChanged = Math.abs(pressY - motionEvent.getY());

            //to find whether it is supposed to be moving horiz or vert,
            //then change only the x or y based on that. no diagonal movement
            if (xChanged > yChanged) {
                movedHoriz = true;
            }
            else {
                movedHoriz = false;
            }

            if(movedHoriz == true) {
                holder.x  = motionEvent.getX() - squareModel.SQUARE_SIZE/2;
            }
            else {
                holder.y = motionEvent.getY() - squareModel.SQUARE_SIZE / 2;
            }

            squareView.invalidate();
        }
        else if (motionEvent.getActionMasked() == 1) { //after square is released
            if (motionEvent.getX() <= squareView.emptySquare.x + squareModel.SQUARE_SIZE
                    && motionEvent.getY() <= squareView.emptySquare.y + squareModel.SQUARE_SIZE
                    && motionEvent.getX() >= squareView.emptySquare.x
                    && motionEvent.getY() >= squareView.emptySquare.y) {
                //swap them, x and y, and in the array itself
                float thirdRaccoonCageX;
                float thirdRaccoonCageY;
                SquareModel thirdRaccoonCage;
                thirdRaccoonCageX = squareView.emptySquare.x;
                thirdRaccoonCageY = squareView.emptySquare.y;
                thirdRaccoonCage = squareView.squares.get(squareView.emptySquareIndex);
                squareView.emptySquare.x  = holderX;
                squareView.emptySquare.y = holderY;
                squareView.squares.set(squareView.emptySquareIndex, holder);
                holder.x = thirdRaccoonCageX;
                holder.y = thirdRaccoonCageY;
                squareView.squares.set(holderIndex, thirdRaccoonCage);
                squareView.emptySquareIndex = holderIndex;

                //if all squares are in the right position, change the square color to magenta
                boolean success = true;
                for(int i = 0; i < squareView.squares.size(); ++i) {
                    //Log.d("loop index's number", String.valueOf(squareView.squares.get(i).number));
                    if(squareView.squares.get(i).number != i+1) {
                        success = false;
                        //Log.d("success", "false");
                        squareView.invalidate();
                    }
                }
                if (success == true) {
                    squareView.squarePaint.setColor(Color.MAGENTA);
                    //Log.d("success", "true");
                    squareView.invalidate();
                }
                squareView.invalidate();
            }
            else { //else, don't do anything. just move it back to its first spot
                holder.x = origX;
                holder.y = origY;
                squareView.invalidate();
            }
        }
        return true;
    }//onTouch


    @Override
    public void onClick(View view) {
        squareView.reset();
        squareView.invalidate();
    }//onClick
}
