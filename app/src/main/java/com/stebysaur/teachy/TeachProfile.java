package com.stebysaur.teachy;

import java.util.ArrayList;

public class TeachProfile {
    String major;
    String courses;
    ArrayList<String> availTimes;

    public TeachProfile(String major, String courses, ArrayList<String> availTimes) {
        this.major = major;
        this.courses = courses;
        this.availTimes = availTimes;
    }
}
