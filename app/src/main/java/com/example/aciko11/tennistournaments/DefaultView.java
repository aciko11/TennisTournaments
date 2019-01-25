package com.example.aciko11.tennistournaments;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aciko11.tennistournaments.Classes.DataStructure;

public class DefaultView extends AppCompatActivity {

    Button btnShowAllPlayers,btnShowAllTournaments, btnSearchPlayer, btnSearchTournament;
    Context context;

    String packageName = "com.example.aciko11.tennistournaments.";
    String urlPlayers = "https://theaciko.000webhostapp.com/TournamentsApi/showPlayers.php";
    String urlTournaments = "https://theaciko.000webhostapp.com/TournamentsApi/showTournaments.php";
    Integer numFieldsPlayers = 3, numFieldsTournaments = 4;
    String dataNamesPlayers[] = {"firstName", "lastName", "id"};
    String dataNamesTournaments[] = {"tournamentName", "tournamentCity", "tournamentRegion", "id"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_view);

        context = this;
        btnShowAllPlayers = findViewById(R.id.btnShowAllPlayers);
        btnShowAllTournaments = findViewById(R.id.btnShowAllTournaments);
        btnSearchPlayer = findViewById(R.id.btnSearchPlayer);
        btnSearchTournament = findViewById(R.id.btnSearchTournament);


        btnSearchPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, searchPlayer.class);
                startActivity(intent);
            }
        });

        btnSearchTournament.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SearchTournament.class);
                startActivity(intent);
            }
        });


        btnShowAllPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ResultShow.class);

                DataStructure data = new DataStructure(numFieldsPlayers);

                for(int i = 0; i < numFieldsPlayers; i++){
                    data.setName(dataNamesPlayers[i], i);
                    data.setValue("", i);
                }

                data.setJsonArrayName("Players");
                data.setIsInsert(false);

                intent.putExtra(packageName + "data", data);
                intent.putExtra(packageName + "url", urlPlayers);

                startActivity(intent);
            }
        });

        btnShowAllTournaments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ResultShow.class);

                DataStructure data = new DataStructure(numFieldsTournaments);

                for(int i = 0; i < numFieldsTournaments; i++){
                    data.setName(dataNamesTournaments[i], i);
                    data.setValue("", i);
                }

                data.setJsonArrayName("Tournaments");
                data.setIsInsert(false);

                intent.putExtra(packageName + "data", data);
                intent.putExtra(packageName + "url", urlTournaments);

                startActivity(intent);
            }
        });
    }
}
