package com.example.mobil_programming;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class work2 extends AppCompatActivity {

    EditText editTextYear;
    EditText editTextMonth;
    EditText editTextDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work2);
    }


    public void do_calculate(View v){


        EditText editTextYear = findViewById(R.id.etv_year);
        EditText editTextMonth = findViewById(R.id.etv_month);
        EditText editTextDay = findViewById(R.id.etv_day);
        TextView tvResult = findViewById(R.id.tv_result);

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        String formattedDate = today.format(formatter);
        System.out.println("Formatted Date: " + formattedDate);

        String dateString = editTextYear.getText().toString() + "-" + editTextMonth.getText().toString() + "-" + editTextDay.getText().toString();
        System.out.println("input date: " + dateString);


        LocalDate startDate = today;
        LocalDate endDate = LocalDate.parse(dateString, formatter);


        long daysDifference = ChronoUnit.DAYS.between(endDate, startDate);



        tvResult.setText(Long.toString(daysDifference));

    }
}