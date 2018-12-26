package com.example.aciko11.tennistournaments;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class searchPlayer extends AppCompatActivity {

    Button btnSearchPlayer;
    TextView txtViewFirstName, txtViewLastName, txtViewPlayerId;
    Context context;

    String fieldKey[], fieldValueKey[], numberOfFieldsKey = "numberOfFields";
    String firstName, lastName, playerId, numberOfFields = "3";
    String url = "https://theaciko.000webhostapp.com/TournamentsApi/searchPlayer.php";
    String packageName = "com.example.aciko11.tennistournaments.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_player);

        txtViewFirstName = findViewById(R.id.txtFirstName);
        txtViewLastName = findViewById(R.id.txtLastName);
        txtViewPlayerId = findViewById(R.id.txtID);




        context = this;
        btnSearchPlayer = findViewById(R.id.btnSearchPlayer);

        btnSearchPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firstName = txtViewFirstName.getText().toString();
                if(firstName == "")
                    firstName = "NULL";
                lastName = txtViewFirstName.getText().toString();
                if(lastName == "")
                    lastName = "NULL";
                playerId = txtViewPlayerId.getText().toString();
                if(playerId == "")
                    playerId = "NULL";

                Intent intent = new Intent(context, ResultShow.class);

                //sending data names
                fieldKey = new String[Integer.parseInt(numberOfFields)];
                for (int i = 0; i < fieldKey.length; i++){
                    fieldKey[i] = packageName + "field" + i;
                }

                intent.putExtra(fieldKey[0], "firstName");
                intent.putExtra(fieldKey[1], "lastName");
                intent.putExtra(fieldKey[2], "id");

                //sending the data
                fieldValueKey = new String[Integer.parseInt(numberOfFields)];
                for(int i = 0; i < fieldValueKey.length; i++){
                    fieldValueKey[i] = packageName + "fieldValue" + i;
                }

                intent.putExtra(fieldValueKey[0], firstName);
                intent.putExtra(fieldValueKey[1], lastName);
                intent.putExtra(fieldValueKey[2], playerId);
                intent.putExtra(packageName + numberOfFieldsKey, numberOfFields);
                intent.putExtra(packageName + "url", url);


                //mi serve il nome del campo (firstname, lastname, id) e il valore

                startActivity(intent);
            }
        });
    }
}
