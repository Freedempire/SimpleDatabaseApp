package com.freedempire.wsdb;

public class StudentModel {
    private String id;
    private String name;
    private String mobile;
    private String course;

    public StudentModel(String id, String name, String mobile, String course) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.course = course;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
