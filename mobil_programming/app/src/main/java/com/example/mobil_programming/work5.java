package com.example.mobil_programming;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class work5 extends AppCompatActivity {

    EditText etPermission;
    ImageView imgPermission;

    String phoneNumber;

    final static  int CAMERA_REQUEST = 5334;
    final static int PERMISSION_REQUEST_CODE = 101;

     static final int SMS_PERMISSION_CODE = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work5);
        etPermission = findViewById(R.id.et_permission);
        imgPermission = findViewById(R.id.img_permession);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public void go_back(MenuItem item) {
        onBackPressed(); // Geri butonuna tıklandığında geri gitme işlevi
    }

    public void get_permission(View v) {
        phoneNumber = etPermission.getText().toString();
        if (!phoneNumber.isEmpty()) {
            takeShot();
        } else {
            Toast.makeText(this, "You should enter a phone number", Toast.LENGTH_SHORT).show();
        }
    }

   public void takeShot() {

       if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
               ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {


           Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
           startActivityForResult(cameraIntent,CAMERA_REQUEST);

       }
   }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap foto = (Bitmap) extras.get("data");
            imgPermission.setImageBitmap(foto); // Load photo into ImageView

            // SMS gönder
            if (!phoneNumber.isEmpty()) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    // Request permission
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_CODE);
                } else {
                    // İzin zaten verilmiş, SMS gönder
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage("tel", null, "Message", null, null);
                    Toast.makeText(getApplicationContext(), "Sent Sms", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                // Permission already granted, send SMS
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNumber, null, "Message", null, null);
                Toast.makeText(getApplicationContext(), "Permission already granted, send SMS", Toast.LENGTH_LONG).show();
            } else {

                Toast.makeText(this, "Permission required to send SMS", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
