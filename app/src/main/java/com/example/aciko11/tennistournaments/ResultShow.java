package com.example.aciko11.tennistournaments;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class ResultShow extends AppCompatActivity {

    Intent intent;
    TableLayout tblLayoutResult;
    Button btnBack;

    JSONObject jsonBody, jsonResponse;
    JSONArray jsonArray;
    RequestQueue requestQueue;

    Integer intNumFields;
    String tempArray[], fieldsNames[], fieldsValues[], numberOfFields;
    String url;
    String packageName = "com.example.aciko11.tennistournaments.";

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_show);

        intent = getIntent();
        context = this;

        //http stuff
        requestQueue = Volley.newRequestQueue(this);
        jsonBody = new JSONObject();

        //tblLayoutResult = (TableLayout) findViewById(R.id.table1);    //(TableLayout) is casting
        tblLayoutResult = findViewById(R.id.resultTableShow);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResultShow.super.finish();
            }

        });

        setNumberOfFields();
        intNumFields = Integer.parseInt(numberOfFields);
        fieldsNames = new String[intNumFields];
        fieldsValues = new String[intNumFields];

        jsonBody = getJsonBody();


        tempArray = fieldsNames;
        //tempArray = new String[intNumFields];
        //tempArray[0] = "Id"; tempArray[1] = "LastName"; tempArray[2] = "FirstName";

        setUrl();
        fetchData(url, jsonBody, "Players");




    }


    public void fetchData(String url, JSONObject jsonBody, final String jsonArrayName){

        //creating a JsonObjectRequest
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,
                jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                jsonResponse = response;
                try {
                    jsonArray = response.getJSONArray(jsonArrayName);
                    Log.i("jsonArray", jsonArray.toString());
                    showData(tempArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("error", error.toString());
                        error.printStackTrace();
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }

    //it creates all the rows with the data in them and puts the result in a table
    public void showData(String columnsNames[]){
        try {
            TableLayout tempTable = new TableLayout(context);
            tempTable.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT,
                    TableLayout.LayoutParams.FILL_PARENT));

            //loops through all the items in the jsonArray
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String column[] = new String[intNumFields];

                //creating an array of cells and a layoutParams to use later
                TextView txtViewTemp[] = new TextView[columnsNames.length];
                ViewGroup.LayoutParams layoutParams = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);

                //creating the cells for the row, setting the layout parameters and their text
                for(int j = 0; j < intNumFields; j++){
                    column[j] = jsonObject.getString(columnsNames[j]);
                    txtViewTemp[j] = new TextView(context);
                    txtViewTemp[j].setText(column[j]);
                    txtViewTemp[j].setLayoutParams(layoutParams);
                }

                //creating a row and setting the layout parameters
                TableRow tableRow = new TableRow(context);
                TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
                tableRow.setLayoutParams(lp);

                for (int j = 0; j < intNumFields; j++){
                    tableRow.addView(txtViewTemp[j]);
                }
                tempTable.addView(tableRow);
            }
            tblLayoutResult.addView(tempTable);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public JSONObject getJsonBody() {

        //get all the fields names
        for (int i = 0; i < intNumFields; i++){
            fieldsNames[i] = intent.getStringExtra(packageName + "field" + i);
        }

        //get their relative values
        for(int i = 0; i < intNumFields; i++){
            fieldsValues[i] = intent.getStringExtra(packageName + "fieldValue" + i);
        }

        //putting the data in the JSONObject
        for(int i = 0; i < intNumFields; i++){
            try {
                jsonBody.put(fieldsNames[i], fieldsValues[i]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        return jsonBody;
        /*try {
            jsonBody.put("lastname", "asd");
            jsonBody.put("firstname", "asd");
            jsonBody.put("id", "1");
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
    }

    public void setNumberOfFields(){
        numberOfFields = intent.getStringExtra(packageName + "numberOfFields");
    }

    public void setUrl(){
        url = intent.getStringExtra(packageName + "url");
    }
}
