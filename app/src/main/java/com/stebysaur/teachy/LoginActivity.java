package com.stebysaur.teachy;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        userLocalStore = new UserLocalStore(this);
    }


    public void onClickLogin(View view) {
        User user = new User(etEmail.getText().toString(), etPassword.getText().toString());


        MongoRequest mongoRequest = new MongoRequest(this);
        mongoRequest.loginUserInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                if (returnedUser == null) {
                    showErrorDialog();
                } else {
                    logUserIn(returnedUser);
                }
            }
        });

        userLocalStore.storeUserData(user);
        userLocalStore.setLoggedInStatus(true);
    }

    public void onClickRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }


    private void showErrorDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage("Incorrect user details.");
        dialogBuilder.setPositiveButton("Ok", null);
        dialogBuilder.show();
    }

    private void logUserIn(User user) {
        userLocalStore.storeUserData(user);
        userLocalStore.setLoggedInStatus(true);

        startActivity(new Intent(this, MainActivity.class));
    }
}
