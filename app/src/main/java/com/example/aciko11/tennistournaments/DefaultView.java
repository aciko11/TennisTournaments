package com.example.aciko11.tennistournaments;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DefaultView extends AppCompatActivity {

    Button btnShowAllPlayers;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_view);

        context = this;
        btnShowAllPlayers = (Button) findViewById(R.id.btnShowAllPlayers);

        btnShowAllPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, showPlayers.class);
                startActivity(intent);
            }
        });
    }
}
