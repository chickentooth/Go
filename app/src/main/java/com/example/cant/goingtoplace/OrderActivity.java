package com.example.cant.goingtoplace;

import android.app.Activity;
import android.os.Bundle;

public class OrderActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        getActionBar().setDisplayShowHomeEnabled(true);
    }
}