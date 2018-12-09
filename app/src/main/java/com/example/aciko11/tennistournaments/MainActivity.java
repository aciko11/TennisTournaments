package com.example.aciko11.tennistournaments;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button btnShow;
    TextView txtViewList;

    RequestQueue requestQueue;
    String showPlayersUrl = "https://theaciko.000webhostapp.com/TournamentsApi/showPlayers.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShow = (Button) findViewById(R.id.button1);
        txtViewList = (TextView) findViewById(R.id.textView2);

        requestQueue = Volley.newRequestQueue(this);


        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("button", "click");
                txtViewList.setText("test");
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Method.POST, showPlayersUrl, null,

                            new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                JSONArray players = null;
                                try {
                                    players = response.getJSONArray("Players");

                                    for (int i = 0; i < players.length(); i++) {
                                        JSONObject player = players.getJSONObject(i);

                                        String id = player.getString("Id");
                                        String lastName = player.getString("LastName");
                                        String firstName = player.getString("FirstName");

                                        txtViewList.append("Id: "+id+", Last Name: "+lastName+
                                        ", First Name: "+firstName);
                                    }
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
        });
    }
}
