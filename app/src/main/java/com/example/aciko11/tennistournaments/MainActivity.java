package com.example.aciko11.tennistournaments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnPlayers, btnJudges;

    //public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //variables instantiation
        btnPlayers = (Button) findViewById(R.id.btnPlayers);
        btnJudges = findViewById(R.id.btnJudges);
        final Context context = this;

        //click listener for the show button
        btnPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //switching activities, in this case to the DefaultView
                Intent intent = new Intent(context, DefaultView.class);
                startActivity(intent);
            }

        });

        btnJudges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, JudgeLogin.class);
                startActivity(intent);
            }
        });
    }
}

