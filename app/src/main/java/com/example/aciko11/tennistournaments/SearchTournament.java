package com.example.aciko11.tennistournaments;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aciko11.tennistournaments.Classes.DataStructure;

public class SearchTournament extends AppCompatActivity {

    Button btnTournamentSearch;
    TextView txtTournamentName, txtTournamentRegion, txtTournamentCity, txtTournamentId;
    String packageName = "com.example.aciko11.tennistournaments.";
    String[] dataNames = {"tournamentName", "tournamentRegion", "tournamentCity", "id"}, dataValues;
    Integer intNumberOfFields = 4;
    String url = "https://theaciko.000webhostapp.com/TournamentsApi/searchTournament.php";

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tournament);

        dataValues = new String[intNumberOfFields];

        btnTournamentSearch = findViewById(R.id.btnSearchTournament);
        txtTournamentName = findViewById(R.id.txtTournamentName);
        txtTournamentRegion = findViewById(R.id.txtTournamentRegion);
        txtTournamentCity = findViewById(R.id.txtTournamentCity);
        txtTournamentId = findViewById(R.id.txtTournamentId);

        context = this;

        btnTournamentSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataValues[0] = txtTournamentName.getText().toString();
                dataValues[1] = txtTournamentRegion.getText().toString();
                dataValues[2] = txtTournamentCity.getText().toString();
                dataValues[3] = txtTournamentId.getText().toString();

                Intent intent = new Intent(context, ResultShow.class);

                DataStructure data = new DataStructure(intNumberOfFields);

                for (int i = 0; i < intNumberOfFields; i++){
                    data.setName(dataNames[i], i);
                    data.setValue(dataValues[i], i);
                }
                data.setJsonArrayName("Tournaments");
                data.setIsInsert(false);

                intent.putExtra(packageName + "url", url);
                intent.putExtra(packageName + "data", data);

                startActivity(intent);
            }
        });
    }
}
