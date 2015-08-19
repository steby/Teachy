package com.stebysaur.teachy;


public class User {

    String name;
    String email;
    String password;
    String school;
    String phone;

    public User(String name, String email, String password, String school, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.school = school;
        this.phone = phone;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", school='" + school + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
