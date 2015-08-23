package com.stebysaur.teachy;

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

public class SetUpTeachActivity extends AppCompatActivity {

    Button btnSaveTeachProfile;
    TextView tvName;
    Spinner spMajors;
    EditText etCourseList;
    CheckedTextView ctvMon1000, ctvMon1030, ctvMon1100, ctvMon1130, ctvMon1200,
            ctvMon1230, ctvMon1300, ctvMon1330;

    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_teach);

        userLocalStore = new UserLocalStore(this);
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

    public void onClickSaveTeachingProfile(View view) {
        //TODO mongorequest and save deets

        //TODO then startactivity and try to load it from mongo
    }





}
