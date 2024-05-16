package com.example.mobil_programming;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class work7 extends AppCompatActivity {

    String URL = "http://vrarch.org/mobil_ders/school.json";

    Spinner spinnerTeacher;
    ListView lwTeacher;

    List<Integer> teacherIDs = new ArrayList<>();

    List<String> codes = new ArrayList<>();
    List<Integer> point = new ArrayList<>();

    int teacherId;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work7);


        spinnerTeacher = findViewById(R.id.spin_teacher);
        lwTeacher = findViewById(R.id.lw_teacher);
        getTeacherInfo();


        lwTeacher.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Tıklanan öğenin metnini al
                String selectedLesson = (String) parent.getItemAtPosition(position);
                getLessonInfo();
                Toast.makeText(getApplicationContext(), "Ders Bilgileri: " + selectedLesson+" Point "+point.get(position)+" Code "+codes.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        spinnerTeacher.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                 teacherId= teacherIDs.get(position);
                codes.clear();
                point.clear();
                getLessonInfo();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Hiçbir öğe seçilmediğinde yapılacak işlemler buraya yazılır
            }
        });

        }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    public void go_back(MenuItem item) {
        onBackPressed(); // Geri butonuna tıklandığında geri gitme işlevi
    }


    void getTeacherInfo(){
        RequestQueue teacherQueue = Volley.newRequestQueue(this);
        JsonObjectRequest teacherRequest = new JsonObjectRequest(Request.Method.GET, URL,null, new Response.Listener<JSONObject>(

        ) {
            @Override
            public void onResponse(JSONObject response) {
                List<String> teacherNames = new ArrayList<>();


                try {

                    // JSONARRAY NOT ITERABLE SO CANT USE FOREACH


                    JSONArray teachers = response.getJSONArray("OgretimElemanlari");
                    for (int i = 0; i < teachers.length(); i++) {
                        JSONObject teacher = teachers.getJSONObject(i);

                        String teacherName = teacher.getString("adi");
                        int teacherId = teacher.getInt("sicil");



                        teacherNames.add(teacherName);
                        teacherIDs.add(teacherId);

                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, teacherNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinnerTeacher.setAdapter(adapter);

                    // İşlenen veriyi kullanabilirsiniz
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }
        );
        teacherQueue.add(teacherRequest);

    }

    void getLessonInfo(){

        RequestQueue lessonQueue = Volley.newRequestQueue(this);

        JsonObjectRequest lessonRequest = new JsonObjectRequest(Request.Method.GET, URL,null, new Response.Listener<JSONObject>(

        ) {
            @Override
            public void onResponse(JSONObject response) {
                List<String> lessons = new ArrayList<>();

                try {

                    // JSONARRAY NOT ITERABLE SO CANT USE FOREACH

                    JSONArray lessonJson = response.getJSONArray("Dersler");
                    for (int i = 0; i < lessonJson.length(); i++) {
                        JSONObject lesson = lessonJson.getJSONObject(i);

                        String lessonName = lesson.getString("Adi");

                        int teacherIdCheck = lesson.getInt("OgretmenSicil");
                        if(teacherId==teacherIdCheck)
                        {
                            lessons.add(lessonName);
                            codes.add(lesson.getString("Kodu"));
                            point.add(lesson.getInt("Kredisi"));
                        }

                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, lessons);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    lwTeacher.setAdapter(adapter);

                    // İşlenen veriyi kullanabilirsiniz
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }


        );
        lessonQueue.add(lessonRequest);
    }

}
