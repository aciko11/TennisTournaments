package com.example.aciko11.tennistournaments;

import android.content.Context;
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

    TableLayout tblLayoutResult;
    Button btnBack;

    RequestQueue requestQueue;
    String showPlayersUrl = "https://theaciko.000webhostapp.com/TournamentsApi/showPlayers.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_players);

        final Context context = this;

        requestQueue = Volley.newRequestQueue(this);

        //tblLayoutResult = (TableLayout) findViewById(R.id.table1);    //TableLayout) is casting
        tblLayoutResult = findViewById(R.id.table1);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(context, MainActivity.class);
                //startActivity(intent);
                showPlayers.super.finish();
            }

        });


        //creating a JsonObjectRequest
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, showPlayersUrl,
            null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            JSONArray players;
                            try {
                                TableLayout tempTable = new TableLayout(context);
                                tempTable.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT,
                                        TableLayout.LayoutParams.FILL_PARENT));

                                //gets all the items named "Players"
                                players = response.getJSONArray("Players");

                                //loops through all the "Players" items
                                for (int i = 0; i < players.length(); i++) {
                                    JSONObject player = players.getJSONObject(i);

                                    String id = player.getString("Id");
                                    String lastName = player.getString("LastName");
                                    String firstName = player.getString("FirstName");

                                    //writes the values to the text view
                                    //txtViewList.append("Id: "+id+", Last Name: "+lastName+
                                    //        ", First Name: "+firstName);

                                    TextView txtViewTemp0 = new TextView(context);
                                    TextView txtViewTemp1 = new TextView(context);
                                    TextView txtViewTemp2 = new TextView(context);

                                    ViewGroup.LayoutParams layoutParams = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);

                                    txtViewTemp0.setText(id);
                                    txtViewTemp0.setLayoutParams(layoutParams);
                                    txtViewTemp1.setText(lastName);
                                    txtViewTemp1.setLayoutParams(layoutParams);
                                    txtViewTemp2.setText(firstName);
                                    txtViewTemp2.setLayoutParams(layoutParams);


                                    TableRow tableRow = new TableRow(context);
                                    TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
                                    tableRow.setLayoutParams(lp);
                                    tableRow.addView(txtViewTemp0);
                                    tableRow.addView(txtViewTemp1);
                                    tableRow.addView(txtViewTemp2);
                                    tempTable.addView(tableRow);
                                }
                                tblLayoutResult.addView(tempTable);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
        },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        requestQueue.add(jsonObjectRequest);
    }
}


