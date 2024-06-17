package com.example.mobil_programming;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class addContact extends AppCompatActivity {

    EditText etPhone;
    EditText etFullName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        etPhone = findViewById(R.id.et_add_contact_phone);
        etFullName = findViewById(R.id.et_add_contact_full_name);

    }


    public void addContentConfirm(View view){

        int id=view.getId();
        if(R.id.btn_add_content_confirm== id){
            String phoneNumber = etPhone.getText().toString();
            String fName = etFullName.getText().toString();

            Intent rIntent = new Intent();

            rIntent.putExtra("data_phoneNumber", phoneNumber);
            rIntent.putExtra("data_fName", fName);

            setResult(Activity.RESULT_OK, rIntent);
            finish();
        }
        else
            System.out.println("Add Content Button Warning");

    }

}