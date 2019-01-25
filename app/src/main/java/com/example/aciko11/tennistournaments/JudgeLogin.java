package com.example.aciko11.tennistournaments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.aciko11.tennistournaments.Classes.DataStructure;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JudgeLogin extends AppCompatActivity {

    TextView txtUsername, txtPassword;
    Button btnLogin;
    Context context;

    RequestQueue requestQueue;

    String url = "https://theaciko.000webhostapp.com/TournamentsApi/login.php";
    String packageName = "com.example.aciko11.tennistournaments.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_judge_login);

        requestQueue = Volley.newRequestQueue(this);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnJudgeLogin);

        context = this;

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                JSONObject jsonBody = new JSONObject();
                try {
                    jsonBody.put("username", txtUsername.getText().toString());
                    jsonBody.put("password", txtPassword.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url
                        , jsonBody, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Boolean login = gotResponse(response);
                        if(login){
                            Intent intent = new Intent(context, JudgeView.class);
                            startActivity(intent);
                        }
                        else{
                            showAlertDialog(context,"Error", "Credentials not valid", "OK", "OK");
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                requestQueue.add(jsonObjectRequest);
            }
        });
    }

    boolean gotResponse(JSONObject res){
        Integer code = 0;
        try {
            Log.i("response", res.toString());
            code = (int) res.get("Code");
            Log.i("resTest", res.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(code == 1){
            return true;
        }
        return false;
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
