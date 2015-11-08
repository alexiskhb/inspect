package com.example.dmitrii.testbtn;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * Created by zimovik007 on 07.11.15.
 */

public class SituationIsBad extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situation_bad);
    }

    EditText text1;
    Spinner spin1;
    RatingBar rate1;

    public class JSONParser {
        InputStream is = null;
        JSONObject jObj = null;
        String json = "";
        public JSONParser() {
        }

        public JSONObject makeHttpRequest(String url, String method, List<NameValuePair> params) {
            try {
                if (method == "POST") {
                    DefaultHttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(url);
                    httpPost.setEntity(new UrlEncodedFormEntity(params));
                    HttpResponse httpResponse = httpClient.execute(httpPost);
                    HttpEntity httpEntity = httpResponse.getEntity();
                    is = httpEntity.getContent();
                } else if (method == "GET") {
                    DefaultHttpClient httpClient = new DefaultHttpClient();
                    String paramString = URLEncodedUtils.format(params, "utf-8");
                    url += "?" + paramString;
                    HttpGet httpGet = new HttpGet(url);
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    HttpEntity httpEntity = httpResponse.getEntity();
                    is = httpEntity.getContent();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();
                json = sb.toString();
            } catch (Exception e) {
                Log.e("Buffer Error", "Error converting result " + e.toString());
            }
            try {
                jObj = new JSONObject(json);
            } catch (JSONException e) {
                Log.e("JSON Parser", "Error parsing data " + e.toString());
            }
            return jObj;
        }
    }

    public class UpdateTask extends AsyncTask<String, Void, JSONObject> {
        Context context;

        public UpdateTask(Context context) {
            super();
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... urls) {
            return loadJSON(urls[0]);
        }

        public JSONObject loadJSON(String url) {

            JSONParser jParser = new JSONParser();
            // здесь параметры необходимые в запрос добавляем
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("text_comment", text1.getText().toString()));
            params.add(new BasicNameValuePair("area_id", String.valueOf(spin1.getSelectedItemPosition())));
            params.add(new BasicNameValuePair("time", String.valueOf(new Date())));
            params.add(new BasicNameValuePair("coord_x", "1.0"));
            params.add(new BasicNameValuePair("coord_y", "1.0"));
            params.add(new BasicNameValuePair("rate", String.valueOf(rate1.getRating())));
            // посылаем запрос методом GET
            JSONObject json = jParser.makeHttpRequest(url, "POST", params);

            return json;
        }

        @Override
        protected void onPostExecute(JSONObject jsonData) {
            if (jsonData != null) {
                super.onPostExecute(jsonData);
                String res = "";
                try {
                    // прочитать параметр, который отправил сервер;
                    // здесь вместо "result" подставляйте то, что вам надо
                    res = jsonData.getString("result");
                    // что-то делаем, к примеру вызываем метод главного Activity на обновление GUI
                    //((MainActivity) context).updateGUI(res);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                //((MainActivity) context).updateGUI(res);
            }
        }

    }

    public void exitActivity(View view){
        this.finish();
    }

    public void SendReview(View view){
        text1 = (EditText) findViewById(R.id.editText2);
        spin1 = (Spinner) findViewById(R.id.spinner);
        rate1 = (RatingBar) findViewById(R.id.ratingBar);
        new UpdateTask(this).execute("http://www.obscure-fjord-5098.herokuapp.com/create_review.php");
    }
}



