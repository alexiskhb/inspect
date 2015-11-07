package com.example.dmitrii.testbtn;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

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




        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://progra2r.bget.ru/handler.php");

        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(6);
        nameValuePair.add(new BasicNameValuePair("text_comment", text1.getText().toString()));
        nameValuePair.add(new BasicNameValuePair("area_id", String.valueOf(spin1.getSelectedItemPosition())));
        nameValuePair.add(new BasicNameValuePair("time", String.valueOf(new Date())));
        nameValuePair.add(new BasicNameValuePair("rate", String.valueOf(rate1.getRating())));
        nameValuePair.add(new BasicNameValuePair("coord_x", "1.0"));
        nameValuePair.add(new BasicNameValuePair("coord_y", "1.0"));


        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            HttpResponse response = httpClient.execute(httpPost);
            Log.d("Http Post Response:", response.toString());
        }
        catch (ClientProtocolException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }


}


