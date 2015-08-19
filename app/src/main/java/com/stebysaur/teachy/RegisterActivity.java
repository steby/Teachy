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

public class RegisterActivity extends AppCompatActivity {

    Button btnRegister;
    EditText etName, etEmail, etPassword, etSchool, etPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etSchool = (EditText) findViewById(R.id.etSchool);
        etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
    }

    public void onClickRegister(View view) {
        User toRegister = new User(etName.getText().toString(),
                etEmail.getText().toString(),
                etPassword.getText().toString(),
                etSchool.getText().toString(),
                etPhoneNumber.getText().toString());

        registerUser(toRegister);
    }

    private void registerUser(User registerUser) {
        ServerRequest serverRequest = new ServerRequest(this);
        serverRequest.storeUserDataInBackground(registerUser, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
}
