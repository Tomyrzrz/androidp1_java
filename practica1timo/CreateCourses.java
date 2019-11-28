package com.softim.practica1timo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.softim.practica1timo.modelos.Teachers;
import com.softim.practica1timo.models.Courses;
import java.util.ArrayList;
import java.util.HashMap;

public class CreateCourses extends AppCompatActivity {

    Button btn_create_c;
    Spinner spn_teachers_cc, spn_groups_cc, spn_career_cc;
    DatabaseReference reference;
    EditText edt_name_course;
    CheckBox english, progra, modeling, admon, networks, bds, expresion, design;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_courses);

        btn_create_c = findViewById(R.id.btn_crear_course);
        spn_teachers_cc = findViewById(R.id.spn_teachers_cc);
        spn_groups_cc = findViewById(R.id.spn_groups_cc);
        spn_career_cc = findViewById(R.id.spn_carrers_cc);
        edt_name_course = findViewById(R.id.edt_name_course);
        english = findViewById(R.id.chb_english);
        progra = findViewById(R.id.chb_progra);
        modeling = findViewById(R.id.chb_modeling);
        admon = findViewById(R.id.chb_admon);
        networks = findViewById(R.id.chb_network);
        bds = findViewById(R.id.chb_bds);
        expresion = findViewById(R.id.chb_expression);
        design = findViewById(R.id.chb_design);


        reference = FirebaseDatabase.getInstance().getReference();

        ArrayList<String> carreras = new ArrayList<>();
        carreras.add("Biotecnologia");
        carreras.add("Gastronomia");
        carreras.add("Tecnologias de Informacion");

        ArrayList<String> grupos = new ArrayList<>();
        grupos.add("1A TIC");
        grupos.add("1B TIC");
        grupos.add("4A TIC");
        grupos.add("7A TIC");
        grupos.add("7B TIC");
        grupos.add("10A TIC");

        ArrayAdapter<String> adaptarCarrera = new ArrayAdapter<>
                (getApplicationContext(), android.R.layout.simple_spinner_item,
                        carreras);
        spn_career_cc.setAdapter(adaptarCarrera);

        ArrayAdapter<String> adaptarGupos = new ArrayAdapter<>
                (getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,
                        grupos);
        spn_groups_cc.setAdapter(adaptarGupos);

        reference.child("Teachers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ArrayList<String> profes = new ArrayList<>();

                for (DataSnapshot data : dataSnapshot.getChildren()){
                    Teachers profe = data.getValue(Teachers.class);
                    if (profe != null){
                        profes.add(profe.getName());
                    }
                }

                ArrayAdapter<String> adapterTeacher = new ArrayAdapter<>
                        (getApplicationContext(),
                                android.R.layout.simple_spinner_dropdown_item, profes);
                spn_teachers_cc.setAdapter(adapterTeacher);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_create_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertDatabase();
            }
        });

    }

    private void insertDatabase() {
        String nombre_t = spn_teachers_cc.getSelectedItem().toString();
        String materias = checarMaterias();
        String grupo = spn_groups_cc.getSelectedItem().toString();
        String carrera = spn_career_cc.getSelectedItem().toString();
        String nombre_curso = edt_name_course.getText().toString();

        if (nombre_t.equals("") || materias.equals("") )
            Toast.makeText(this,"Fill all fields",
                    Toast.LENGTH_SHORT).show();
        else {
            final ProgressDialog dialogo = new ProgressDialog(this);
            dialogo.setMessage("Please wait......");
            dialogo.setTitle("Services UTOM");
            dialogo.setCanceledOnTouchOutside(false);
            dialogo.setCancelable(false);
            dialogo.show();

            String uid = FirebaseAuth.getInstance().getUid();
            Courses course = new Courses(carrera, grupo,
                    materias, nombre_t, uid, nombre_curso);
            reference.child("Courses").push().setValue(course)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dialogo.dismiss();
                        Toast.makeText(getApplicationContext(),"All correct", Toast.LENGTH_SHORT).show();
                        cleanInterface();
                    }
                });
        }

    }

    private void cleanInterface() {
        edt_name_course.setText("");
        admon.setChecked(false);
        english.setChecked(false);
        expresion.setChecked(false);
    }

    private String checarMaterias() {
        String maters = "";
        if (english.isChecked())
            maters += "English,";
        if (admon.isChecked())
            maters += "Administration,";
        if (networks.isChecked())
            maters += "Networks,";
        if (progra.isChecked())
            maters += "Programmation,";
        if (modeling.isChecked())
            maters += "Modeling,";
        if (bds.isChecked())
            maters += "DataBase,";
        if (expresion.isChecked())
            maters += "Expresion,";
        if (design.isChecked())
            maters += "Design,";

        return maters;
    }


}



















