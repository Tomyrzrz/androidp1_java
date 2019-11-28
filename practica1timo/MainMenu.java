package com.softim.practica1timo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainMenu extends AppCompatActivity {

    ImageView btn_register, btn_assign_c_s, btn_assign_t_a, btn_create_c, btn_list_all, btn_list_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        btn_register = findViewById(R.id.img_register_student_menu);
        btn_list_data = findViewById(R.id.img_data_course_menu);
        btn_list_all = findViewById(R.id.img_all_courses_menu);
        btn_create_c = findViewById(R.id.img_create_course_menu);
        btn_assign_t_a = findViewById(R.id.img_assign_teacher_menu);
        btn_assign_c_s = findViewById(R.id.img_assign_courses_student_menu);

        btn_assign_c_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent window1 = new Intent(getApplicationContext(), AssignCoursesStudent.class);
                startActivity(window1);
            }
        });

        btn_assign_t_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent window1 = new Intent(getApplicationContext(), AssignTeacherArea.class);
                startActivity(window1);
            }
        });

        btn_create_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent window1 = new Intent(getApplicationContext(), CreateCourses.class);
                startActivity(window1);
            }
        });

        btn_list_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent window1 = new Intent(getApplicationContext(), ListAllCourses.class);
                startActivity(window1);
            }
        });

        btn_list_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent window1 = new Intent(getApplicationContext(), ListDataCourse.class);
                startActivity(window1);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent window1 = new Intent(getApplicationContext(), RegisterStudent.class);
                startActivity(window1);
            }
        });

    }
}
