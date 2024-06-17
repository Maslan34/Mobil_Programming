package com.example.mobil_programming;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class work8 extends AppCompatActivity implements View.OnClickListener {

    Button btnNews;
    ListView lvList;

    Document doc;
    @Override

    protected void onCreate(Bundle savedInstanceState ){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work8);

        btnNews=findViewById(R.id.btn_get_news);

        lvList = findViewById(R.id.lv_news);

        btnNews.setOnClickListener(this);

        String URL = "http://vrarch.org/en";


        RequestQueue queue= Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        doc=Jsoup.parse(response);
                        System.out.println("here");
                        Toast.makeText(getApplicationContext(),"Downloading completed succesfully",Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error",error.getMessage());
            }
        });
        queue.add(request);


    }



    @Override
    public void onClick(View view) {

        ArrayList<String> list=new ArrayList<>();

        Element downSection = doc.getElementById("down-section");
        Elements divs = downSection.children();

        for (Element element:divs) {

            Elements subDiv = element.children();
            Element mainDiv = subDiv.first();
            Elements h6Elements = mainDiv.getElementsByTag("h6");
            Element h6 = h6Elements.first();
            Element aElement = h6.getElementsByTag("a").first();

            //System.out.println(" Content: " + aElement.text());
            list.add( aElement.text());

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        lvList.setAdapter(adapter);



    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    public void go_back(MenuItem item) {
        onBackPressed(); // Geri butonuna tıklandığında geri gitme işlevi
    }

}
