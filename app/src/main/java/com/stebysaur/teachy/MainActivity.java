package com.stebysaur.teachy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    UserLocalStore userLocalStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userLocalStore = new UserLocalStore(this);
    }


    @Override
    protected void onStart() {
        super.onStart();

        if (!userLocalStore.getLoggedInStatus()) {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void onClickTeach(MenuItem item) {

    }


    public void onClickProfile(MenuItem item) {
        startActivity(new Intent(this, ProfileActivity.class));
    }

    public void onClickLogout(MenuItem item) {
        userLocalStore.clearUserData();
        userLocalStore.setLoggedInStatus(false);

        startActivity(new Intent(this, LoginActivity.class));
    }
}
