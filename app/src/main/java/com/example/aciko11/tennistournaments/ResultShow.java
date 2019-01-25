package com.example.aciko11.tennistournaments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import com.example.aciko11.tennistournaments.Classes.DataStructure;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ResultShow extends AppCompatActivity {

    Intent intent;
    TableLayout tblLayoutResult;
    Button btnBack;

    JSONObject jsonBody, jsonResponse;
    JSONArray jsonArray;
    RequestQueue requestQueue;

    DataStructure data;
    int intNumFields;
    String url;
    String packageName = "com.example.aciko11.tennistournaments.";
    String[] isResultNames = {"ResultCode", "ResultValue"};

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_show);

        intent = getIntent();
        context = this;

        //get all the data
        this.data = (DataStructure) intent.getSerializableExtra(packageName+"data");
        intNumFields = data.getDataSize();

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


        jsonBody = getJsonBody();


        setUrl();
        fetchData(url, jsonBody);




    }


    public void fetchData(String url, JSONObject jsonBody){

        //creating a JsonObjectRequest
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,
                jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                jsonResponse = response;
                try {
                    jsonArray = response.getJSONArray(data.getJsonArrayName());
                    Log.i("jsonArray", jsonArray.toString());
                    showData();

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
    public void showData(){
        try {
            TableLayout tempTable = new TableLayout(context);
            tempTable.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT,
                    TableLayout.LayoutParams.FILL_PARENT));



            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
            ViewGroup.LayoutParams layoutParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1f);

            TableRow firstRow = new TableRow(context);
            firstRow.setLayoutParams(lp);

            TextView txtViewFirstRow[] = new TextView[data.getDataSize()];
            for(int i = 0; i < data.getDataSize(); i++){
                txtViewFirstRow[i] = new TextView(context);
                txtViewFirstRow[i].setText(data.getName(i));
                txtViewFirstRow[i].setLayoutParams(layoutParams);
                firstRow.addView(txtViewFirstRow[i]);
            }

            tempTable.addView(firstRow);

            //loops through all the items in the jsonArray
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String column[] = new String[data.getDataSize()];

                //creating an array of cells and a layoutParams to use later
                TextView txtViewTemp[] = new TextView[data.getDataSize()];

                if(data.getIsInsert()){
                    for(int j = 0; j < 2; j++){
                        column[j] = jsonObject.getString(isResultNames[j]);
                        txtViewTemp[j] = new TextView(context);
                        txtViewTemp[j].setText(column[j]);
                        txtViewTemp[j].setLayoutParams(layoutParams);
                    }
                }
                else{
                    //creating the cells for the row, setting the layout parameters and their text
                    for(int j = 0; j < intNumFields; j++){
                        column[j] = jsonObject.getString(data.getName(j));
                        txtViewTemp[j] = new TextView(context);
                        txtViewTemp[j].setText(column[j]);
                        txtViewTemp[j].setLayoutParams(layoutParams);
                    }
                }

                //creating a row and setting the layout parameters
                TableRow tableRow = new TableRow(context);
                tableRow.setLayoutParams(lp);
                if(data.getIsInsert()){
                    for (int j = 0; j < 2; j++){
                        tableRow.addView(txtViewTemp[j]);
                    }
                }
                else{
                    for (int j = 0; j < intNumFields; j++){
                        tableRow.addView(txtViewTemp[j]);
                    }
                }

                tempTable.addView(tableRow);
            }
            tblLayoutResult.addView(tempTable);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void showResult(){

    }


    public JSONObject getJsonBody() {
        JSONObject jsonObj = new JSONObject();
        //putting the data in a JSONObject
        for(int i = 0; i < intNumFields; i++){
            try {
                Log.i("jsonBody", data.getName(i)+data.getValue(i));
                jsonObj.put(data.getName(i), data.getValue(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return jsonObj;
    }


    public void setUrl(){
        url = intent.getStringExtra(packageName + "url");
    }
}
