package com.example.mobil_programming;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class work3 extends AppCompatActivity {



    ImageView imgView;

    TextView tvTitle,tvPlate,tvExplanation;

    ImageButton btnLeft,btnRight;
    int index=0;

    String[] city = new String[]{"istanbul","ankara","izmir","bursa","antalya","adana","samsun","trabzon","konya","gaziantep"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work3);
        imgView = findViewById(R.id.city_view);
        imgView.setImageResource(R.drawable.istanbul);

        tvTitle=findViewById(R.id.tv_city_title);
        tvPlate=findViewById(R.id.tv_city_licance_plate);
        tvExplanation=findViewById(R.id.tv_city_explanation);

        tvTitle.setText("City:istanbul");
        tvPlate.setText(R.string.plate_istanbul);
        tvExplanation.setText(R.string.istanbul);

        btnLeft = findViewById(R.id.btn_left_change_view);
        btnRight= findViewById(R.id.btn_right_change_view);

        btnLeft.setVisibility(View.INVISIBLE);


    }

    public  void changeView(View view){
        int id=view.getId();

        if(R.id.btn_right_change_view== id){
            index++;
            if(index!=9 && index !=0){
                btnRight.setVisibility(View.VISIBLE);
                btnLeft.setVisibility(View.VISIBLE);
            }
            else{
                btnRight.setVisibility(View.INVISIBLE);
            }

            do_changing();
        } else if (R.id.btn_left_change_view== id) {
            index--;
            if(index!=9 && index !=0){
                btnRight.setVisibility(View.VISIBLE);
                btnLeft.setVisibility(View.VISIBLE);
            }
            else{
                btnLeft.setVisibility(View.INVISIBLE);
            }

            do_changing();
        }
        else
            System.out.println("Warning");
    }

    public void do_changing(){
        tvTitle.setText("City:"+city[index]);

        switch (city[index]){
            case "istanbul":
                tvPlate.setText(R.string.plate_istanbul);
                tvExplanation.setText(R.string.istanbul);
                imgView.setImageResource(R.drawable.istanbul);
                break;
            case "ankara":
                tvPlate.setText(R.string.plate_ankara);
                tvExplanation.setText(R.string.ankara);
                imgView.setImageResource(R.drawable.ankara);
                break;
            case "izmir":
                tvPlate.setText(R.string.plate_izmir);
                tvExplanation.setText(R.string.izmir);
                imgView.setImageResource(R.drawable.izmir);
                break;
            case "bursa":
                tvPlate.setText(R.string.plate_bursa);
                tvExplanation.setText(R.string.bursa);
                imgView.setImageResource(R.drawable.bursa);
                break;
            case "antalya":
                tvPlate.setText(R.string.plate_antalya);
                tvExplanation.setText(R.string.antalya);
                imgView.setImageResource(R.drawable.antalya);
                break;
            case "adana":
                tvPlate.setText(R.string.plate_adana);
                tvExplanation.setText(R.string.adana);
                imgView.setImageResource(R.drawable.adana);
                break;
            case "samsun":
                tvPlate.setText(R.string.plate_samsun);
                tvExplanation.setText(R.string.samsun);
                imgView.setImageResource(R.drawable.samsun);
                break;
            case "trabzon":
                tvPlate.setText(R.string.plate_trabzon);
                tvExplanation.setText(R.string.trabzon);
                imgView.setImageResource(R.drawable.trabzon);
                break;
            case "konya":
                tvPlate.setText(R.string.plate_konya);
                tvExplanation.setText(R.string.konya);
                imgView.setImageResource(R.drawable.konya);
                break;
            case "gaziantep":
                tvPlate.setText(R.string.plate_gaziantep);
                tvExplanation.setText(R.string.gaziantep);
                imgView.setImageResource(R.drawable.gaziantep);
                break;
        }


    }

}