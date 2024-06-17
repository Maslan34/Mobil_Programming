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

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
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
        else if (R.id.btn_work6 == objId) {
            intent = new Intent(MainActivity.this, work6.class);
            startActivity(intent);
        }
        else if (R.id.btn_work7 == objId) {
            intent = new Intent(MainActivity.this, work7.class);
            startActivity(intent);
        }
        else if (R.id.btn_work8 == objId) {
            intent = new Intent(MainActivity.this, work8.class);
            startActivity(intent);
        }
        else if (R.id.btn_work9 == objId) {
            intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        else if (R.id.btn_work10 == objId) {
            intent = new Intent(MainActivity.this, work10.class);
            startActivity(intent);
        }
        else {
            System.out.println("Warning");
        }
    }

}