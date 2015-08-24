package com.stebysaur.teachy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

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

        setContentView(R.layout.fragment_not_teacher);

        User user = userLocalStore.getLoggedInUserData();

        MongoRequest mongoRequest = new MongoRequest(this);
        mongoRequest.loginUserInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                userLocalStore.storeUserData(returnedUser);
                if (returnedUser.isTeacher) {
                    setContentView(R.layout.activity_teach);
                }
            }
        });
    }

    public void onClickSetUpTeachingProfile(View view) {
        startActivity(new Intent(this, SetUpTeachActivity.class));
    }


    public void onClickModifyTeachingProfile(View view) {
        Toast.makeText(this, "Not implemented yet", Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_teach, menu);
        return true;
    }
}
