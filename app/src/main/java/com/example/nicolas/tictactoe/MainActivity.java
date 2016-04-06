package com.example.nicolas.tictactoe;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    int activePlayer = 0; //  0 = yellow, 1 = red, 2 = empty

    boolean gameIsActive = true;

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningCombinations = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};


    public void fadeIn(View viewTapped) {

        ImageView token = (ImageView) viewTapped;

        token.animate().alpha((float) 1).setDuration((long) 1000);

        int tappedToken = Integer.parseInt(token.getTag().toString());

        if (gameState[tappedToken] == 2 && gameIsActive) {

            gameState[tappedToken] = activePlayer;

            if (activePlayer == 0) {

                token.setImageResource(R.drawable.yellow);

                activePlayer = 1;

            } else {

                token.setImageResource(R.drawable.red);

                activePlayer = 0;

            }

            for (int[] winningPosition : winningCombinations) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {

                    // Someone has won!

                    gameIsActive = false;

                    String winner = "Red";

                    if (gameState[winningPosition[0]] == 0) {

                        winner = "Yellow";

                    }

                    TextView winnerMessage = (TextView) findViewById(R.id.textView);

                    winnerMessage.setText(winner + " has won!");

                    LinearLayout layout = (LinearLayout) findViewById(R.id.endGame);

                    layout.setVisibility(View.VISIBLE);

                } else {

                    boolean gameIsOver = true;

                    for (int counterState : gameState) {

                        if (counterState == 2) gameIsOver = false;

                    }

                    if (gameIsOver) {

                        TextView winnerMessage = (TextView) findViewById(R.id.textView);

                        winnerMessage.setText("It's a draw");

                        LinearLayout layout = (LinearLayout) findViewById(R.id.endGame);

                        layout.setVisibility(View.VISIBLE);

                    }

                }
            }
        }
    }

    public void playAgain(View view) {

        gameIsActive = true;

        LinearLayout layout = (LinearLayout)findViewById(R.id.endGame);

        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        for (int i = 0; i < gameState.length; i++) {

            gameState[i] = 2;

        }

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        for (int i = 0; i< gridLayout.getChildCount(); i++) {

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.nicolas.tictactoe/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.nicolas.tictactoe/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
