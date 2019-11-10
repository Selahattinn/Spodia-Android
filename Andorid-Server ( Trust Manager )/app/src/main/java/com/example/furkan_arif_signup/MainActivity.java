package com.example.furkan_arif_signup;

import android.net.SSLCertificateSocketFactory;
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

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;


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
                String uri = "https://10.8.61.140:8080/login"; //SERVER ADRESİ

                X509TrustManager manager = null;
                FileInputStream fs = null;
                try {
                    TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                    KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
                    fs = new FileInputStream(System.getProperty("javax.net.ssl.trustStore"));
                    keyStore.load(fs, null);


                    trustManagerFactory.init(keyStore);
                    TrustManager[] managers = trustManagerFactory.getTrustManagers();

                    for (TrustManager tm : managers) {
                        if (tm instanceof X509TrustManager) {
                            manager = (X509TrustManager) tm;
                            break;
                        }
                    }
                }
                catch(NullPointerException e)
                {
                    if (fs != null) { fs.close(); }
                }
                catch(NoSuchAlgorithmException e){
                    System.out.println( e);
                }
                catch(CertificateException e){
                    System.out.println( e);
                }
                catch(KeyStoreException e){
                        System.out.println( e);
                }


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