package com.softim.practica1timo.modelos;

public class Teachers {

    private String name = "";
    private String last_name = "";
    private String profession = "";
    private String career = "";
    private String uid = "";

    public Teachers() {
    }

    public Teachers(String name, String last_name, String profession, String career, String uid) {
        this.name = name;
        this.last_name = last_name;
        this.profession = profession;
        this.career = career;
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }
}
