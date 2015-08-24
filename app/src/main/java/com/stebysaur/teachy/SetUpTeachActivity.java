package com.stebysaur.teachy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mongodb.Mongo;

import java.util.ArrayList;

public class SetUpTeachActivity extends AppCompatActivity {

    Button btnSaveTeachProfile;
    TextView tvName;
    Spinner spMajors;
    EditText etCourseList;
    CheckedTextView ctvMon1000, ctvMon1030, ctvMon1100, ctvMon1130, ctvMon1200,
            ctvMon1230, ctvMon1300, ctvMon1330;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_teach);

        UserLocalStore userLocalStore = new UserLocalStore(this);
        User user = userLocalStore.getLoggedInUserData();

        tvName = (TextView) findViewById(R.id.tvName);
        tvName.setText(user.name);

        btnSaveTeachProfile = (Button) findViewById(R.id.btnSaveTeachProfile);
        etCourseList = (EditText) findViewById(R.id.etCourseList);
        spMajors = (Spinner) findViewById(R.id.spMajors);

        ctvMon1000 = (CheckedTextView) findViewById(R.id.ctvMon1000);
        ctvMon1030 = (CheckedTextView) findViewById(R.id.ctvMon1030);
        ctvMon1100 = (CheckedTextView) findViewById(R.id.ctvMon1100);
        ctvMon1130 = (CheckedTextView) findViewById(R.id.ctvMon1130);
        ctvMon1200 = (CheckedTextView) findViewById(R.id.ctvMon1200);
        ctvMon1230 = (CheckedTextView) findViewById(R.id.ctvMon1230);
        ctvMon1300 = (CheckedTextView) findViewById(R.id.ctvMon1300);
        ctvMon1330 = (CheckedTextView) findViewById(R.id.ctvMon1330);

        ArrayAdapter<CharSequence> spAdapter = ArrayAdapter.createFromResource(this,
                R.array.majors_supported, android.R.layout.simple_spinner_item);
        spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMajors.setAdapter(spAdapter);
    }


    public void onClickCheckMark(View view) {
        CheckedTextView ctv = (CheckedTextView) view;

        if(ctv.isChecked())
            ctv.setChecked(false);
        else
            ctv.setChecked(true);
    }

    public void onClickSaveTeachingProfile(View view) {
        //TODO mongorequest and save deets
        //save major
        //save course list
        //save avail times

        final UserLocalStore userLocalStore = new UserLocalStore(this);
        User user = userLocalStore.getLoggedInUserData();

        String major = spMajors.getSelectedItem().toString();
        String courses = etCourseList.getText().toString();
        ArrayList<String> availTimes = new ArrayList<>();

        if (ctvMon1000.isChecked())
            availTimes.add(ctvMon1000.getText().toString());
        if (ctvMon1030.isChecked())
            availTimes.add(ctvMon1030.getText().toString());
        if (ctvMon1100.isChecked())
            availTimes.add(ctvMon1100.getText().toString());
        if (ctvMon1130.isChecked())
            availTimes.add(ctvMon1130.getText().toString());
        if (ctvMon1200.isChecked())
            availTimes.add(ctvMon1200.getText().toString());
        if (ctvMon1230.isChecked())
            availTimes.add(ctvMon1230.getText().toString());
        if (ctvMon1300.isChecked())
            availTimes.add(ctvMon1300.getText().toString());
        if (ctvMon1330.isChecked())
            availTimes.add(ctvMon1330.getText().toString());

        Toast.makeText(this, "Available: " + availTimes.toString(), Toast.LENGTH_LONG).show();


        TeachProfile teachProfile = new TeachProfile(major, courses, availTimes);

        MongoRequest mongoRequest = new MongoRequest(this);
        mongoRequest.saveTeachingProfileInBackground(user, teachProfile, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                startActivity(new Intent(SetUpTeachActivity.this, TeachActivity.class));
            }
        });

        //TODO then startactivity and try to load it from mongo
    }


}
