package com.stebysaur.teachy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class TeachActivity extends AppCompatActivity {

    UserLocalStore userLocalStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userLocalStore = new UserLocalStore(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        User user = userLocalStore.getLoggedInUserData();

        if (!user.isTeacher) {
            setContentView(R.layout.fragment_not_teacher);
        } else {
            setContentView(R.layout.activity_teach);
        }
    }

    public void onClickSetUpTeachingProfile(View view) {
        startActivity(new Intent(this, SetUpTeachActivity.class));
    }


    public void onClickModifyTeachingProfile(View view) {



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_teach, menu);
        return true;
    }
}
