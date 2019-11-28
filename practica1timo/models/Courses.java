package com.softim.practica1timo.models;

public class Courses {

    private String carrera = "";
    private String grupo = "";
    private String materias = "";
    private String teacher = "";
    private String uid = "";
    private String name_course = "";

    public Courses(String carrera, String grupo, String materias, String teacher, String uid, String name_course) {
        this.carrera = carrera;
        this.grupo = grupo;
        this.materias = materias;
        this.teacher = teacher;
        this.uid = uid;
        this.name_course = name_course;
    }

    public Courses() {
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getMaterias() {
        return materias;
    }

    public void setMaterias(String materias) {
        this.materias = materias;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName_course() {
        return name_course;
    }

    public void setName_course(String name_course) {
        this.name_course = name_course;
    }
}

