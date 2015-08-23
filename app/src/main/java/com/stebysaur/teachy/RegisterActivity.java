package com.stebysaur.teachy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class RegisterActivity extends AppCompatActivity {

    Button btnRegister;
    EditText etName, etEmail, etPassword, etPhoneNumber;
    Spinner spSchools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        spSchools = (Spinner) findViewById(R.id.spSchools);
        etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);


        //Adapter for Schools Spinner
        ArrayAdapter<CharSequence> spAdapter = ArrayAdapter.createFromResource(this,
                R.array.schools_supported, android.R.layout.simple_spinner_item);
        spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSchools.setAdapter(spAdapter);

        //Auto obtain phone number
        TelephonyManager tMgr = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        etPhoneNumber.setText(tMgr.getLine1Number());

    }

    public void onClickRegister(View view) {
        User toRegister = new User(etName.getText().toString(),
                etEmail.getText().toString(),
                etPassword.getText().toString(),
                spSchools.getSelectedItem().toString(),
                etPhoneNumber.getText().toString());

        registerUser(toRegister);
    }

    private void registerUser(User registerUser) {
        MongoRequest mongoRequest = new MongoRequest(this);
        mongoRequest.registerUserInBackground(registerUser, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        //TODO: can preload email for user at login screen after registering
    }
}
