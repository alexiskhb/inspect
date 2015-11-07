package com.example.dmitrii.testbtn;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by zimovik007 on 07.11.15.
 */
public class SituationIsBad extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situation_bad);
    }


    public void exitActivity(View view){
        this.finish();
    }

    public void SendReview(View view){
        makePostRequest();
    }

    private void makePostRequest(){
        EditText text1 = (EditText) findViewById(R.id.editText2);
        Spinner spin1 = (Spinner) findViewById(R.id.spinner);
        RatingBar rate1 = (RatingBar) findViewById(R.id.ratingBar);

        Toast toast = Toast.makeText(getApplicationContext(),
                String.valueOf(rate1.getRating()), Toast.LENGTH_SHORT);
        toast.show();


    }


}


