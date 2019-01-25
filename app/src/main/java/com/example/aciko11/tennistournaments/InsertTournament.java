package com.example.aciko11.tennistournaments;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aciko11.tennistournaments.Classes.DataStructure;

import java.io.IOException;
import java.util.List;

public class InsertTournament extends AppCompatActivity {

    LocationManager locationManager;
    Button btnAutoLocation, btnInsert;
    Double longitude, latitude;
    Context context;
    TextView test, txtTournamentName, txtTournamentRegion, txtTournamentCity;
    String[] values = new String[3];
    String[] names = {"tournamentName", "tournamentRegion", "tournamentCity"};

    String url = "https://theaciko.000webhostapp.com/TournamentsApi/insertTournament.php";
    String packageName = "com.example.aciko11.tennistournaments.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_tournament);

        context = this;

        txtTournamentCity = findViewById(R.id.txtJudgeTournamentCity);
        txtTournamentName = findViewById(R.id.txtJudgeTournamentName);
        txtTournamentRegion = findViewById(R.id.txtJudgeTournamentRegion);

        btnAutoLocation = findViewById(R.id.btnAutoLocation);
        btnInsert = findViewById(R.id.btnJudgeInsertTournament);
        test = findViewById(R.id.txtViewTest);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        btnAutoLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED){

                    ActivityCompat.requestPermissions(InsertTournament.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION
                        , Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }

                Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                test.setText(latitude.toString()+longitude.toString());
                getLocationData(location);
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                values[0] = txtTournamentName.getText().toString();
                values[1] = txtTournamentRegion.getText().toString();
                values[2] = txtTournamentCity.getText().toString();

                Intent intent = new Intent(context, ResultShow.class);
                DataStructure data = new DataStructure(3);

                for(int i = 0; i < 3; i++){
                    data.setName(names[i], i);
                    data.setValue(values[i], i);
                }
                data.setJsonArrayName("Result");
                data.setIsInsert(true);

                intent.putExtra(packageName+"data", data);
                intent.putExtra(packageName+"url", url);

                startActivity(intent);
            }
        });
    }

    void getLocationData(Location location){
        Geocoder geocoder = new Geocoder(this);
        String city = "", state = "";
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if(addresses.size() == 0){
                showAlertDialog(context, "Error", "The location was not found try again",
                        "Cancel", "Ok");
            }
            else{
                city = addresses.get(0).getLocality();
                state = addresses.get(0).getAdminArea();
                test.setText(addresses.get(0).toString());
            }

            txtTournamentRegion.setText(state);
            txtTournamentCity.setText(city);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Dialog for errors
    public static void showAlertDialog(Context context, String title, String message, String posBtnMsg, String negBtnMsg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(posBtnMsg, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
