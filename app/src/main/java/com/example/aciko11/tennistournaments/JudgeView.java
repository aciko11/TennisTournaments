package com.example.aciko11.tennistournaments;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class JudgeView extends AppCompatActivity {

    Button btnInsertPlayer, btnInsertTournament;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_judge_view);

        context = this;

        btnInsertPlayer = findViewById(R.id.btnJudgePlayer);
        btnInsertTournament = findViewById(R.id.btnJudgeTournament);

        btnInsertPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, InsertPlayer.class);
                startActivity(intent);
            }
        });

        btnInsertTournament.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, InsertTournament.class);
                startActivity(intent);
            }
        });

    }
}
