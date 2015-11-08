package com.example.dmitrii.testbtn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by zimovik007 on 07.11.15.
 */
public class WhatSituation extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_situation);
    }

    public void find_Situation(View v){
        Intent intent_find = new Intent(WhatSituation.this, AreaSituation.class);
        startActivity(intent_find);
    }
}
