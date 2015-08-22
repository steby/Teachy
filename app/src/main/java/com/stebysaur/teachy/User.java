package com.stebysaur.teachy;


public class User {

    String name;
    String email;
    String password;
    String school;
    String phone;
    boolean isTeacher;


    public User(String name, String email, String password, String school, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.school = school;
        this.phone = phone;
    }


    public User(String name, String email, String password, String school, String phone, boolean isTeacher) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.school = school;
        this.phone = phone;
        this.isTeacher = isTeacher;
    }


    //to query login
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
