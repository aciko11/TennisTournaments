package com.example.aciko11.tennistournaments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.aciko11.tennistournaments.Classes.DataStructure;

public class showPlayers extends AppCompatActivity {

    String packageName = "com.example.aciko11.tennistournaments.";
    String url = "https://theaciko.000webhostapp.com/TournamentsApi/showPlayers.php";
    String numberOfFields = "3";
    Integer intNumFields;
    String fieldNames[];
    //String data[][];
    DataStructure data[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_players);

        intNumFields = Integer.parseInt(numberOfFields);

        fieldNames = new String[Integer.parseInt(numberOfFields)];
        fieldNames[0] = "firstName";
        fieldNames[1] = "lastName";
        fieldNames[2] = "id";


        final Context context = this;

        Intent intent = new Intent(context, ResultShow.class);
        intent.putExtra(packageName + "numberOfFields", numberOfFields);
        intent.putExtra(packageName + "url", url);
        for(int i = 0; i < Integer.parseInt(numberOfFields); i++){
            intent.putExtra(packageName + "field" + i, fieldNames[i]);
        }

        intent.putExtra(packageName + "data", data);
        startActivity(intent);

        };
    }


