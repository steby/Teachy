package com.stebysaur.teachy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class TeachActivity extends AppCompatActivity {

    UserLocalStore userLocalStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teach);
        userLocalStore = new UserLocalStore(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        User user = userLocalStore.getLoggedInUserData();

        if (!user.isTeacher) {
            setContentView(R.layout.fragment_not_teacher);
        }
    }

    public void onClickModifyProfile(View view) {



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_teach, menu);
        return true;
    }
}
