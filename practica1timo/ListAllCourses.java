package com.softim.practica1timo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.softim.practica1timo.models.Courses;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.ViewHolder;

import java.util.ArrayList;

public class ListAllCourses extends AppCompatActivity {

    RecyclerView rcv_cursos;
    DatabaseReference reference;
    ArrayList<Courses> listCursos;
    CoursesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_courses);

        reference = FirebaseDatabase.getInstance().getReference();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        rcv_cursos = findViewById(R.id.recicler_cursos);
        rcv_cursos.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rcv_cursos.setLayoutManager(manager);

        listCursos = new ArrayList<>();
        obtenerCursos();
    }

    private void obtenerCursos(){
        reference.child("Courses").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot datas : dataSnapshot.getChildren()){
                    Courses cour = datas.getValue(Courses.class);
                    listCursos.add(cour);
                }
                adapter = new CoursesAdapter(ListAllCourses.this, listCursos);
                rcv_cursos.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Something was wrong!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
