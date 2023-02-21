package com.example.fifteensquare_mocker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SquareView square = findViewById(R.id.squareView);
        SquareController controller = new SquareController(square);
        //square.setOnTouchListener(controller);

        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
        square.setOnTouchListener(controller);

        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(controller);
    }//onCreate
}