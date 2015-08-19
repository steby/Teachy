package com.stebysaur.teachy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnLogout;
    EditText etName, etEmail, etPassword, etSchool, etPhoneNumber;
    UserLocalStore userLocalStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogout = (Button) findViewById(R.id.btnLogout);
        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etSchool = (EditText) findViewById(R.id.etSchool);
        etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);

        userLocalStore = new UserLocalStore(this);
    }


    @Override
    protected void onStart() {
        super.onStart();

        if (isAuthenticated()) {
            displayUserDetails();
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    private boolean isAuthenticated() {
        return userLocalStore.getLoggedInStatus();
    }

    private void displayUserDetails() {
        User user = userLocalStore.getLoggedInUserData();

        etName.setText(user.name);
        etEmail.setText(user.email);
        etSchool.setText(user.school);
        etPhoneNumber.setText(user.phone);
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onClickLogout(MenuItem item) {
        userLocalStore.clearUserData();
        userLocalStore.setLoggedInStatus(false);

        startActivity(new Intent(this, LoginActivity.class));
    }
}
