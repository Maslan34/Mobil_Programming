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
        Intent intent;
        if (R.id.btn_work1 == objId) {
            intent = new Intent(MainActivity.this, work1.class);
             startActivity(intent);
        } else if (R.id.btn_work2 == objId) {
             intent = new Intent(MainActivity.this, work2.class);
             startActivity(intent);
        }else if (R.id.btn_work3 == objId) {
            intent = new Intent(MainActivity.this, work3.class);
            startActivity(intent);
        }
        else if (R.id.btn_work4 == objId) {
            intent = new Intent(MainActivity.this, work4.class);
            startActivity(intent);
        }
        else if (R.id.btn_work5 == objId) {
            intent = new Intent(MainActivity.this, work5.class);
            startActivity(intent);
        }
        else {
            System.out.println("Warning");
        }
    }

}