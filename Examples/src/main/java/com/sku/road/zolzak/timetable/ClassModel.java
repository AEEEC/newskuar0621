package com.sku.road.zolzak.timetable;

public class ClassModel {

    private String classname;
    private String professor;
    private String classtime;
    private String classroom;
    private String isugubun;
    private String classscore;

    private String classcode;
    private String grade;


    public String getClassroom() {
        return classroom;
    }
    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getClassname() {
        return classname;
    }
    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getProfessor() {
        return professor;
    }
    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getClasstime() {
        return classtime;
    }
    public void setClasstime(String classtime) {
        this.classtime = classtime;
    }

    public String getIsugubun() {
        return isugubun;
    }
    public void setIsugubun(String isugubun) {
        this.isugubun = isugubun;
    }

    public String getClassscore() {
        return classscore;
    }
    public void setClassscore(String classscore) {
        this.classscore = classscore;
    }

    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getClasscode() {
        return classcode;
    }
    public void setClasscode(String classcode) {
        this.classcode = classcode;
    }


    public ClassModel(){}



}