package com.example.dmitrii.testbtn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ClickBtn(View view) {
        Intent intent_map = new Intent(MainActivity.this, MapActivity.class);
        startActivity(intent_map);
    }

    public void ClickFirstButton(View view){
        Intent intent_what_situation = new Intent(MainActivity.this, WhatSituation.class);
        startActivity(intent_what_situation);
    }

    public void ClickSecondButton(View view){
        Intent intent_situation_bad = new Intent(MainActivity.this, SituationIsBad.class);
        startActivity(intent_situation_bad);
    }
}
