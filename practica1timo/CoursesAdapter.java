package com.softim.practica1timo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softim.practica1timo.models.Courses;
import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

import java.util.ArrayList;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Courses> cursos;

    CoursesAdapter(Context pcontext, ArrayList<Courses> pcursos){
        context = pcontext;
        cursos = pcursos;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_course, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name_carrer.setText(cursos.get(position).getCarrera());
        holder.name_group.setText(cursos.get(position).getGrupo());
        holder.name_subjects.setText(cursos.get(position).getMaterias());
        holder.name_teacher.setText(cursos.get(position).getTeacher());
    }

    @Override
    public int getItemCount() {
        return cursos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name_carrer, name_teacher, name_subjects, name_group;

        private MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name_carrer = itemView.findViewById(R.id.txt_name_carrer);
            name_teacher = itemView.findViewById(R.id.txt_name_teacher);
            name_subjects = itemView.findViewById(R.id.txt_name_subjects);
            name_group = itemView.findViewById(R.id.txt_name_group);
        }
    }
}
