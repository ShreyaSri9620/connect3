package com.example.dell.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0 for circle and 1 for cross
    int currentPlayer =0;
    boolean gameActive= true;

    int[] gameState={2,2,2,2,2,2,2,2,2};

    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn(View view){

        ImageView strike=(ImageView) view;//since we only are concern with the imageView that the user will tap among the 9 imageView

        String winnerName="";
        int index=Integer.parseInt(strike.getTag().toString());

        if(gameState[index] == 2 && gameActive) {

            gameState[index]=currentPlayer;

            strike.setTranslationY(-1000f);
            if (currentPlayer == 0) {

                strike.setImageResource(R.drawable.zero);
                currentPlayer = 1;

            } else {

                strike.setImageResource(R.drawable.cross2);
                currentPlayer = 0;

            }
            strike.animate().translationYBy(1000f).setDuration(100);
            for(int[] winningPosition : winningPositions)
            {
                if(gameState[winningPosition[0]] == gameState[winningPosition[1]]
                        && gameState[winningPosition[1]]==gameState[winningPosition[2]]
                    && gameState[winningPosition[0]] != 2)
                {
                    System.out.println(gameState[winningPosition[0]]);

                    //Someone has won!!
                    gameActive=false;
                    if(gameState[winningPosition[0]]== 0){
                        winnerName="Player 1";
                    }
                    else
                    {
                        winnerName="Player 2";
                    }
                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMsg);
                    winnerMessage.setText(winnerName + " has won!");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.winnerLayout);
                    layout.setVisibility(View.VISIBLE);
                }else {

                     boolean gameOver=true;

                    for(int counterState : gameState)
                    {
                        if(counterState == 2) gameOver = false;
                    }
                    if(gameOver){
                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMsg);
                        winnerMessage.setText("It's a draw!!");
                        LinearLayout layout = (LinearLayout) findViewById(R.id.winnerLayout);
                        layout.setVisibility(View.VISIBLE);
                    }
                }



            }
            }
        }
public void playAgain(View view)
{
    gameActive=true;
    LinearLayout layout = (LinearLayout) findViewById(R.id.winnerLayout);
    layout.setVisibility(View.INVISIBLE);
    GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

    currentPlayer =0;

    for(int i = 0; i<gameState.length; i++)
    {
        gameState[i]=2;
    }
    for(int i =0; i<gridLayout.getChildCount();i++){

        ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
    }

}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
