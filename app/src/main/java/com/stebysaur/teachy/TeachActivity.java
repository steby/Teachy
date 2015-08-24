package com.stebysaur.teachy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TeachActivity extends AppCompatActivity {

    UserLocalStore userLocalStore;
    TextView tvName, tvMajor, tvCourses, tvAvailTimes;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teach);
        userLocalStore = new UserLocalStore(this);

        tvName = (TextView) findViewById(R.id.tvName);
        tvMajor = (TextView) findViewById(R.id.tvMajor);
        tvCourses = (TextView) findViewById(R.id.tvCourses);
        tvAvailTimes = (TextView) findViewById(R.id.tvAvailTimes);
    }

    @Override
    protected void onStart() {
        super.onStart();


        user = userLocalStore.getLoggedInUserData();

        MongoRequest mongoRequest = new MongoRequest(this);
        mongoRequest.loginUserInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                userLocalStore.storeUserData(returnedUser);
                if (returnedUser.isTeacher) {
                    loadTeachProfile();
                } else {
                    setContentView(R.layout.fragment_not_teacher);
                }
            }
        });
    }

    private void loadTeachProfile() {
        MongoRequest mongoRequest = new MongoRequest(this);
        mongoRequest.loadTeachingProfileInBackground(user, new GetTeachProfileCallback() {
            @Override
            public void done(TeachProfile returnedTeachProfile) {
                displayTeachProfile(returnedTeachProfile);
            }
        });
    }


    private void displayTeachProfile(TeachProfile teachProfile) {
        tvMajor.setText(teachProfile.major);
        tvName.setText(user.name);
        tvCourses.setText(teachProfile.courses);
        tvAvailTimes.setText(teachProfile.availTimes.toString());
    }

    public void onClickSetUpTeachingProfile(View view) {
        startActivity(new Intent(this, SetUpTeachActivity.class));
    }


    public void onClickModifyTeachingProfile(View view) {
        startActivity(new Intent(this, SetUpTeachActivity.class));
    }
}
