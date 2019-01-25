package com.example.aciko11.tennistournaments;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aciko11.tennistournaments.Classes.DataStructure;

public class InsertPlayer extends AppCompatActivity {

    String[] values = {"", ""};
    String[] names = {"firstName", "lastName"};
    TextView txtPlayerFirstName, txtPlayerLastName;
    Button btnInsertPlayer;
    Context context;

    String url = "https://theaciko.000webhostapp.com/TournamentsApi/insertPlayer.php";
    String packageName = "com.example.aciko11.tennistournaments.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_player);

        context = this;

        txtPlayerFirstName = findViewById(R.id.txtJudgePlayerFirstName);
        txtPlayerLastName = findViewById(R.id.txtJudgePlayerLastName);
        btnInsertPlayer = findViewById(R.id.btnJudgeInsertPlayer);

        btnInsertPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                values[0] = txtPlayerFirstName.getText().toString();
                values[1] = txtPlayerLastName.getText().toString();

                Intent intent = new Intent(context, ResultShow.class);
                DataStructure data = new DataStructure(2);

                for(int i = 0; i < 2; i++){
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
}
