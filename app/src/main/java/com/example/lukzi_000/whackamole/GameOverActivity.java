package com.example.lukzi_000.whackamole;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        ((TextView)findViewById(R.id.scoreTextView)).setText(GameActivity.score + "");
        GameActivity.game=false;
    }
}
