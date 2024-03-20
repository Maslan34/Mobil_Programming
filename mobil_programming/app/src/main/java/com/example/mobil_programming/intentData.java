package com.example.mobil_programming;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class intentData extends AppCompatActivity {

    EditText etData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_data);
        etData=findViewById(R.id.et_intent_data);

    }

    public void  send_intent_data (View v){

        String data = etData.getText().toString();
        int counter = 0;

        StringBuilder sb = new StringBuilder();
        for (char c : data.toCharArray()) {
            if (Character.isLetter(c)) {
                sb.append(c);
            } else {
                counter++;
            }
        }
        String filteredData = sb.toString();

        Intent rIntent = new Intent();


        rIntent.putExtra("string_data", filteredData);
        rIntent.putExtra("string_data_counter", counter);

        setResult(Activity.RESULT_OK, rIntent);
        finish();
    }





}