package com.example.dmitrii.testbtn;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import android.widget.TextView;

/**
 * Created by zimovik007 on 07.11.15.
 */
public class SituationIsBad extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situation_bad);
    }


    public void exitActivity(View v){
        this.finish();
    }

    public void SendReview(View v){
        makePostRequest();
    }

    private void makePostRequest(){
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("www.example.com");

        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(6);
        nameValuePair.add(new BasicNameValuePair("text_comment", ""));
        nameValuePair.add(new BasicNameValuePair("area_id", ""));
        nameValuePair.add(new BasicNameValuePair("time", ""));
        nameValuePair.add(new BasicNameValuePair("rate", ""));
        nameValuePair.add(new BasicNameValuePair("coord_x", ""));
        nameValuePair.add(new BasicNameValuePair("coord_y", ""));


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


