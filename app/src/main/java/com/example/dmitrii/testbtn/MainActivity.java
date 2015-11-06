package com.example.dmitrii.testbtn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //private TextView HelloTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //HelloTextView = (TextView)findViewById(R.id.textViewid);
    }

    public void ClickBtn(View view) {
        //HelloTextView.setText("Hello Kitty!");
        Intent intent_map = new Intent(MainActivity.this, MapActivity.class);
        startActivity(intent_map);
    }

    public void ClickFirstButton(View view){
        Intent intent_first_button = new Intent(MainActivity.this, WhatSituation.class);
        startActivity(intent_first_button);
    }

    public void ClickSecondButton(View view){
        Intent intent_second_button = new Intent(MainActivity.this, SituationIsBad.class);
        startActivity(intent_second_button);
    }

    /*public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, MapActivity.class);
        startActivity(intent);
    }*/
}
