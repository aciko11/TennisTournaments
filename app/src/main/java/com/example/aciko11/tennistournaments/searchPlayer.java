package com.example.aciko11.tennistournaments;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aciko11.tennistournaments.Classes.DataStructure;

public class searchPlayer extends AppCompatActivity {

    Button btnSearchPlayer;
    TextView txtViewFirstName, txtViewLastName, txtViewPlayerId;
    Context context;

    String url = "https://theaciko.000webhostapp.com/TournamentsApi/searchPlayer.php";
    String packageName = "com.example.aciko11.tennistournaments.";
    String dataNames[] = {"firstName", "lastName", "id"};
    String dataValues[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_player);

        txtViewFirstName = findViewById(R.id.txtFirstName);
        txtViewLastName = findViewById(R.id.txtLastName);
        txtViewPlayerId = findViewById(R.id.txtID);

        dataValues = new String[3];


        context = this;
        btnSearchPlayer = findViewById(R.id.btnSearchPlayer);

        btnSearchPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dataValues[0] = txtViewFirstName.getText().toString();
                dataValues[1] = txtViewLastName.getText().toString();
                dataValues[2] = txtViewPlayerId.getText().toString();

                Intent intent = new Intent(context, ResultShow.class);

                DataStructure data = new DataStructure(3);

                for (int i = 0; i < 3; i++){
                    data.setName(dataNames[i], i);
                    data.setValue(dataValues[i], i);
                }
                data.setJsonArrayName("Players");
                data.setIsInsert(false);

                intent.putExtra(packageName + "data", data);
                intent.putExtra(packageName + "url", url);

                startActivity(intent);
            }
        });
    }
}
