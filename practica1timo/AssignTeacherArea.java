package com.softim.practica1timo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.softim.practica1timo.modelos.Teachers;
import com.softim.practica1timo.models.Courses;

import java.util.ArrayList;

public class AssignTeacherArea extends AppCompatActivity {

    Spinner spn_carreras;
    EditText edt_name, edt_last, edt_profesion;
    Button btn_crear_p;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_teacher_area);

        spn_carreras = findViewById(R.id.spn_carrers_utom);
        edt_name = findViewById(R.id.edt_name_teacher);
        edt_last = findViewById(R.id.edt_lastname_teacher);
        edt_profesion = findViewById(R.id.edt_profession_teacher);
        btn_crear_p = findViewById(R.id.btn_register_teacher);

        reference = FirebaseDatabase.getInstance().getReference();

        ArrayList<String> carreras = new ArrayList<>();
        carreras.add("Biotecnologia");
        carreras.add("Gastronomia");
        carreras.add("Tecnologias de Informacion");

        ArrayAdapter<String> adaptarCarrera = new ArrayAdapter<>
                (getApplicationContext(), android.R.layout.simple_spinner_item,
                        carreras);
        spn_carreras.setAdapter(adaptarCarrera);

        btn_crear_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearteacher();
            }
        });
    }

    private void crearteacher() {
        String nombre_t = edt_name.getText().toString();
        String last = edt_last.getText().toString();
        String profess = edt_profesion.getText().toString();
        String carre = spn_carreras.getSelectedItem().toString();

        if (nombre_t.equals("") || last.equals("") )
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
            Teachers course = new Teachers(nombre_t, last,
                    profess, carre, uid);
            reference.child("Teachers").push().setValue(course)
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
        edt_profesion.setText("");
        edt_last.setText("");
        edt_name.setText("");
    }


}
