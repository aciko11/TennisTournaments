package com.example.aciko11.tennistournaments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class showPlayers extends AppCompatActivity {

    String packageName = "com.example.aciko11.tennistournaments.";
    String url = "https://theaciko.000webhostapp.com/TournamentsApi/showPlayers.php";
    String numberOfFields = "3";
    String fieldNames[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_players);

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

        startActivity(intent);

        };
    }


