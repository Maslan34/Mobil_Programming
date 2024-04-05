package com.example.mobil_programming;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class updateContact extends AppCompatActivity {

    EditText etPhone;
    EditText etFullName;

    long user_id;
    dataBaseController dbController =new dataBaseController(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);

        etPhone = findViewById(R.id.et_update_contact_phone);
        etFullName = findViewById(R.id.et_update_contact_full_name);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user_id = extras.getLong("data_id_update");
        }

        Cursor cursorValues=dbController.getContact(user_id);

        if (cursorValues != null && cursorValues.moveToFirst()) {
            do {

                String fName = cursorValues.getString(1);
                String phone = cursorValues.getString(2);
                System.out.println("fName"+fName+"-");
                etPhone.setText(phone);
                etFullName.setText(fName);

            } while (cursorValues.moveToNext()); // Bir sonraki satıra geç

            cursorValues.close(); // Cursor'u kapat
        }



    }


    public void btn_update_contact(View v){

        this.dbController.updateOne(etFullName.getText().toString(),etPhone.getText().toString(),user_id);
        Intent rIntent=new Intent();

        rIntent.putExtra("contact_data_phone_return", etPhone.getText().toString());
        rIntent.putExtra("contact_data_fName_return", etFullName.getText().toString());
        rIntent.putExtra("contact_data_id_return", user_id);
        System.out.println("-"+etPhone.getText().toString()+"-"+etFullName.getText().toString()+"-"+user_id+"");
        setResult(Activity.RESULT_OK, rIntent);
        finish();


    }
}