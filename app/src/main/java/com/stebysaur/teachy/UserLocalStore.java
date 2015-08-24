package com.stebysaur.teachy;


import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class UserLocalStore {

    public static final String SP_NAME = "UserDetails";

    SharedPreferences userLocalData;

    public UserLocalStore(Context context) {
        userLocalData = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    public void storeUserData(User user) {
        SharedPreferences.Editor spEditor = userLocalData.edit();

        spEditor.putString("name", user.name);
        spEditor.putString("email", user.email);
        spEditor.putString("password", user.password);
        spEditor.putString("school", user.school);
        spEditor.putString("phone", user.phone);
        spEditor.putBoolean("isTeacher", user.isTeacher);
        spEditor.commit();
    }


    public User getLoggedInUserData() {
        String name = userLocalData.getString("name", "");
        String email = userLocalData.getString("email", "");
        String password = userLocalData.getString("password", "");
        String school = userLocalData.getString("school", "");
        String phone = userLocalData.getString("phone", "");

        return new User(name, email, password, school, phone);
    }


    public void setLoggedInStatus(boolean loggedIn) {
        SharedPreferences.Editor spEditor = userLocalData.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }


    public boolean getLoggedInStatus() {
        return userLocalData.getBoolean("loggedIn", false);
    }


    public void clearUserData() {
        SharedPreferences.Editor spEditor = userLocalData.edit();
        spEditor.clear();
        spEditor.commit();
    }
}
