package com.example.mobil_programming;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class work6 extends AppCompatActivity {

    int REQUEST_ADD_CODE=0000;

    int REQUEST_CALL =0002;

    int REQUEST_ADD_UPDATE=0001;

    String callNumber="";
    ListView lwContact;
    boolean procesess =false;

    long id;

    String procesesName="";

    // NEDEN THIS ?
    dataBaseController dbController;

    ArrayList<Contact> contacts = new ArrayList<>();


    ArrayList<Long> contactIds = new ArrayList<Long>();


    ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work6);

        lwContact = findViewById(R.id.lw_contact);

        dbController = new dataBaseController(this);


        adapter = new ContactAdapter(this, contacts);

        Cursor cursorValues=dbController.getContacts();


        if (cursorValues != null && cursorValues.moveToFirst()) {
            do {
                id = cursorValues.getInt(0);
                String fName = cursorValues.getString(1);
                String phone = cursorValues.getString(2);
                Contact contact = new Contact(id,fName,phone);

                contacts.add(contact);
                contactIds.add(id);

            } while (cursorValues.moveToNext()); // Bir sonraki satıra geç

            cursorValues.close(); // Cursor'u kapat
        }


        lwContact.setAdapter(adapter);

        lwContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               if(procesess){
                   long contactId = contactIds.get(position);
                   if(procesesName.equals("delete")){
                       dbController.deleteOne(contactId);
                       contacts.remove(position);
                       contactIds.remove(position);
                        refreshScreen();
                       Toast.makeText(work6.this,   "Contact Deleted", Toast.LENGTH_SHORT).show();
                   }
                   else if (procesesName.equals("update")){


                       Intent intent = new Intent(work6.this, updateContact.class);
                       intent.putExtra("data_id_update", contactId); // Correctly pass the contact ID

                       startActivityForResult(intent, REQUEST_ADD_UPDATE);


                   }

                   else if (procesesName.equals("call")){

                       Cursor cursorValues = dbController.getContact(contactIds.get(position));
                       if (cursorValues != null && cursorValues.moveToFirst()) {
                           do {

                               callNumber = cursorValues.getString(2); //

                           } while (cursorValues.moveToNext());

                           cursorValues.close();
                       }
                       Call();
                   }

               }

                procesess=false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.work6_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_item_call) {
            Toast.makeText(work6.this,  "Please enter a number to call! ", Toast.LENGTH_SHORT).show();
            procesess=true;
            procesesName="call";
        }
        else if (id == R.id.menu_item_delete) {
            Toast.makeText(work6.this,  "Please enter a number to delete! ", Toast.LENGTH_SHORT).show();
            procesess=true;
            procesesName="delete";
        }
        else if (id == R.id.menu_item_close_app) {
            // AlertDialog.Builder oluşturuluyor
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Close App ?");
            builder.setMessage("Are you sure to close app?");

            builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Uygulamayı kapatmak için Intent oluşturuluyor
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("EXIT", true);
                    startActivity(intent);
                }
            });


            builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Kullanıcı "Hayır" dediğinde dialog kapatılacak
                    dialog.dismiss();
                }
            });

            // AlertDialog gösteriliyor
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else if (id == R.id.menu_item_update) {
            Toast.makeText(work6.this,  "Please enter a number to update! ", Toast.LENGTH_SHORT).show();
            procesess=true;
            procesesName="update";
        }
        else if (id == R.id.menu_item_new_contact) {
            Intent intent =new Intent(work6.this,addContact.class);
            startActivityForResult(intent,REQUEST_ADD_CODE);
        }
        else
            System.out.println("Warning menu");
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUEST_CALL)
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
                Call();
            else
                Toast.makeText(this, "User denied the call permission", Toast.LENGTH_SHORT).show();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ADD_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                String rFName = data.getStringExtra("data_fName");
                String rPhone = data.getStringExtra("data_phoneNumber");
                dbController.newContact(rFName, rPhone);
            }
        } else if (requestCode == REQUEST_ADD_UPDATE) {
            if (resultCode == Activity.RESULT_OK) {
                String rFName = data.getStringExtra("contact_data_fName_return");
                String rPhone = data.getStringExtra("contact_data_phone_return");
                long user_id = data.getLongExtra("contact_data_id_return", 0);

                dbController.updateOne(rFName, rPhone, user_id);
            }
        }

        refreshScreen();
        System.out.println("here");
    }

    private void refreshScreen(){
       contactIds.clear();
        contacts.clear();;
        ArrayList<Contact> newContacts = new ArrayList<>();


        Cursor cursorValues = dbController.getContacts();
        if (cursorValues != null && cursorValues.moveToFirst()) {
            do {
                long user_id = cursorValues.getLong(0);
                String fName = cursorValues.getString(1);
                String phone = cursorValues.getString(2);

                //System.out.println(user_id);

                contactIds.add(user_id);

                Contact contact = new Contact(user_id,fName,phone);
                //System.out.println(contact.getfName());
                //System.out.println(contact.getPhone());
                //System.out.println(contact.getPhone());
                System.out.println(contact);
                newContacts.add(contact);


            } while (cursorValues.moveToNext());

            cursorValues.close();
        }

        adapter.clear();

        adapter.addAll(newContacts);
        contacts=newContacts;
        System.out.println(adapter.getCount());
        adapter.notifyDataSetChanged();
    }




    private void Call() {
        Intent phoneIntent=new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:"+callNumber));
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED)
            startActivity(phoneIntent);
        else {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CALL_PHONE)){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Info");
                builder.setMessage("This application needs CALL permision to call someone");
                builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }

            String permissions[]= new String[]{Manifest.permission.CALL_PHONE};
            ActivityCompat.requestPermissions(this,permissions , REQUEST_CALL);
        }
    }

}

