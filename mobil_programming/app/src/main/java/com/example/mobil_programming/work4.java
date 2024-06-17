package com.example.mobil_programming;

import androidx.activity.result.ActivityResult;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class work4 extends AppCompatActivity {


    TextView tvData,tvCounter;
    final static  int REQUEST_CODE = 5353;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work4);
        tvData = findViewById(R.id.tv_result_data);
        tvCounter = findViewById(R.id.tv_intent_count);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public void go_back(MenuItem item) {
        onBackPressed(); // Geri butonuna tıklandığında geri gitme işlevi
    }

    public void getIntentData(View v){

        Intent intent = new Intent(this,intentData.class);
        startActivityForResult(intent,REQUEST_CODE);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE)
            if(resultCode == Activity.RESULT_OK){
                String rValueString = data.getStringExtra("string_data");
                int rValueInt = data.getIntExtra("string_data_counter",0);
                tvData.setText("Formatted: "+rValueString);
                tvCounter.setText("Counter: "+rValueInt);

            }




    }
}