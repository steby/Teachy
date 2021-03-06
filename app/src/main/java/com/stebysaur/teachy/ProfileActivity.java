package com.stebysaur.teachy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class ProfileActivity extends AppCompatActivity {

    EditText etName, etEmail, etSchool, etPhoneNumber;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etSchool = (EditText) findViewById(R.id.etSchool);
        etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);

        userLocalStore = new UserLocalStore(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (userLocalStore.getLoggedInStatus()) {
            displayUserDetails();
        }
    }

    private void displayUserDetails() {
        User user = userLocalStore.getLoggedInUserData();

        etName.setText(user.name);
        etEmail.setText(user.email);
        etSchool.setText(user.school);
        etPhoneNumber.setText(user.phone);
    }


    public void onClickLogout(MenuItem item) {
        userLocalStore.clearUserData();
        userLocalStore.setLoggedInStatus(false);

        startActivity(new Intent(this, LoginActivity.class));
    }

    public void onClickHome(MenuItem item) {
        startActivity(new Intent(this, MainActivity.class));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }
}
