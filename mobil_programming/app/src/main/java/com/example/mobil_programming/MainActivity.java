package com.example.mobil_programming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void goWork(View view) {

        int objId = view.getId();

        if (R.id.btn_work1 == objId) {
            Intent work1Intent = new Intent(MainActivity.this, work1.class);
             startActivity(work1Intent);
        }
        else if (R.id.btn_work2 == objId) {

            Intent work2Intent = new Intent(MainActivity.this, work1.class);
            startActivity(work2Intent);
        }
        else if (R.id.btn_work3 == objId) {

            Intent work2Intent = new Intent(MainActivity.this, work2.class);
            startActivity(work2Intent);
        }
        else {
            System.out.println("Warning");
        }
    }

}