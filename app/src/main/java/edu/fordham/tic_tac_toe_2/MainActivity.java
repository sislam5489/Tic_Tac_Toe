package edu.fordham.tic_tac_toe_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //-1: circle, 1: cross, 0: empty
    int gameState[] = {0, 0, 0, 0, 0, 0, 0, 0, 0};

    //-1: circle, 1: cross
    int activePlayer = -1;

    int winningPositions[][] = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startGame(View view) {
        Log.i("mobdev", "Hello");
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 0;
        }
        activePlayer = -1;

        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView v = (ImageView) gridLayout.getChildAt(i);
            v.setImageDrawable(null);

        }
    }

    public void dropIn(View view) {
        ImageView v = (ImageView) view;
        int counter = Integer.parseInt(view.getTag().toString()) - 1;
        Log.i("mobdev", "view " + counter);
        if(gameState[counter] == 0){
            gameState[counter] = activePlayer; // update the state of cell #counter
            if(activePlayer == -1){
                v.setImageResource(R.drawable.circle);
            }else{ //activePlayer == 1
                v.setImageResource(R.drawable.cross);
            }

            //Animation
            v.setAlpha(0f);
            v.animate().alpha(1f).setDuration(1000);

            //check winning condition
            for(int winningPosition[]: winningPositions){
                if(gameState[winningPosition[0]] == -1
                        && gameState[winningPosition[1]] == -1
                        && gameState[winningPosition[2]] == -1){
                    //-1 has won: circle
                    Log.i("mobdev", "Circle has won!");
                    Toast.makeText(this, "Circle has won", Toast.LENGTH_LONG).show();
                    return;
                }else if(gameState[winningPosition[0]] == 1
                        && gameState[winningPosition[1]] == 1
                        && gameState[winningPosition[2]] == 1){
                    Log.i("mobdev", "Cross has won!");
                    Toast.makeText(this,  "Cross has won",Toast.LENGTH_LONG).show();
                    return;
                }

            }

            activePlayer = -activePlayer;
        }
    }
}