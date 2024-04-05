package com.example.mobil_programming;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdapter extends ArrayAdapter<Contact> {
    public ContactAdapter(Context context, ArrayList<Contact> contacts) {
        super(context, 0, contacts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contact contact = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_contact_item, parent, false);
        }

        TextView tvName = convertView.findViewById(R.id.contact_full_name);
        TextView tvPhone = convertView.findViewById(R.id.contact_phone);

        tvName.setText(contact.getfName());
        tvPhone.setText(contact.getPhone());

        return convertView;
    }
}
