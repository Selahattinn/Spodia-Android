package com.example.furkan_arif_signup;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import java.io.IOException;



public class MainActivity extends AppCompatActivity  {

    TextView textView;
    Button ButtonOK;
    Boolean exec = false;
    String kontrol;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView2);
        ButtonOK = (Button) findViewById(R.id.button);



        ButtonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleTask simpleTask = new SimpleTask();
                String asd = "asd"; //BURAYI SİLME SAKIN!
                simpleTask.execute(asd);

                if(exec){
                    textView.setText("executed");
                    textView.setText(kontrol);
                }
                else
                {
                    textView.setText("not executed");
                }
            }
        });

    }

    private class SimpleTask extends AsyncTask<String, Void, String> {


        private String resp;

        @Override
        protected void onPreExecute() {
            // Create Show ProgressBar
        }

            protected String doInBackground(String... voids)   {

            try {
                String uri = "https://www.google.com"; //SERVER ADRESİ
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet(uri);
                request.addHeader("name", "selahattin");
                request.addHeader("password", "asdqwezxc");


                HttpResponse response1 = client.execute(request);
                kontrol = (response1.getStatusLine().toString());
                exec = true;
            }

            catch (IOException e){
                e.printStackTrace();
                exec = false;

            }
            return  resp;

                /**
                 * Alttaki try catch dursun ilerde kullanılır.

            try {
                HttpPost httpPost = new HttpPost("https://www.google.com");
                ArrayList<NameValuePair> nvps = new ArrayList<NameValuePair>();
                nvps.add(new BasicNameValuePair("name", "selahattin"));
                nvps.add(new BasicNameValuePair("password", "asdqwezxc"));


                httpPost.setEntity(new UrlEncodedFormEntity(nvps));
                HttpResponse response2 = client.execute(httpPost);
                exec = true;
            }
            catch (IOException e){
                e.printStackTrace();
                exec  =false;

            }*/


        }

    }



}