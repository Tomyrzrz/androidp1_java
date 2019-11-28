package com.softim.practica1timo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.softim.practica1timo.models.Courses;

import java.util.ArrayList;

public class ListDataCourse extends AppCompatActivity {

    Spinner spn_course;
    String data_course = "";
    TextView txt_data_course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data_course);

        spn_course = findViewById(R.id.spn_courses_dc);
        txt_data_course = findViewById(R.id.txt_data_of_course);
        DatabaseReference referencia = FirebaseDatabase.getInstance()
                .getReference("Courses");

        referencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ArrayList<String> nombres_coursos = new ArrayList<>();

                for (DataSnapshot course : dataSnapshot.getChildren()){
                    Courses datos = course.getValue(Courses.class);
                    if (datos != null)
                        nombres_coursos.add(datos.getName_course());
                }

                ArrayAdapter<String> adaterCourses = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.model_spinner, nombres_coursos);
                spn_course.setAdapter(adaterCourses);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        spn_course.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                final String valorSelected = adapterView.getSelectedItem().toString();

                DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference();
                reference2.child("Courses").orderByChild("name_course")
                    .equalTo(valorSelected).limitToFirst(1)
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            data_course = "";
                            for (DataSnapshot dato : dataSnapshot.getChildren()){
                                Courses objeto = dato.getValue(Courses.class);
                                if (objeto != null){
                                    data_course += "Course: " + objeto.getName_course() + "\n";
                                    data_course += "Career: " + objeto.getCarrera() + "\n";
                                    data_course += "Teacher: " + objeto.getTeacher() + "\n";
                                    data_course += "Group: " + objeto.getGrupo() + "\n";
                                    data_course += "Subjects: " + objeto.getMaterias();
                                }
                            }

                            txt_data_course.setText(data_course);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
}
