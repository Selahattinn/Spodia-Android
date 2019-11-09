package com.example.furkan_arif_signup;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import org.apache.http.HttpResponse;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


import java.io.IOException;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity  {

    TextView textView;
    Button ButtonOK;
    Boolean exec = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView2);
        ButtonOK = (Button) findViewById(R.id.button);



        ButtonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SimpleTask().execute();
                if(exec){
                    textView.setText("executed");
                }
                else
                {
                    textView.setText("not executed");
                }
            }
        });

    }

    private class SimpleTask extends AsyncTask<String, Void, String> {




        @Override
        protected void onPreExecute() {
            // Create Show ProgressBar
        }

        protected String doInBackground(String... urls)   {
            String result = "";
            String uri = "http://127.0.0.1:1200/login";
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(uri);
            request.addHeader("name", "selahattin");
            request.addHeader("password", "asdqwezxc");



            try {
                HttpResponse response1 = client.execute(request);
                System.out.println(response1.getStatusLine());
                exec = true;

            }
            catch (IOException e){
                exec = false;
            }

            HttpPost httpPost = new HttpPost("http://127.0.0.1:1200/login");
            ArrayList<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("name", "selahattin"));
            nvps.add(new BasicNameValuePair("password", "asdqwezxc"));
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nvps));
                HttpResponse response2 = client.execute(httpPost);
                exec = true;
            }
            catch (IOException e){
                exec  =false;

            }








            /*try {
                HttpResponse response = client.execute(request);
                exec = true;
                /*

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        if (response.isSuccessful()) {


                        } else {

                        }
                        /*request.headers().;
                        final String myResponse = response.body().string();
                        System.out.println(response.headers().get);
                        System.out.println("a")


                    }
                });


            } catch (IOException e) {
                exec = false;

            }*/


            return result;
        }

        @Override
        protected void onPostExecute(String result)  {
            super.onPostExecute(result);
        }
    }



}