package com.example.clink;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Login extends Activity
{
    public EditText username;
    public EditText password;
    public Button button;
    public View view;
    public TextView textView;

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            new NukeSSLCerts().nuke();
            username = (EditText) findViewById(R.id.username);
            password = (EditText) findViewById(R.id.password);
            textView = (TextView) findViewById(R.id.textView);
            button = (Button) findViewById(R.id.login);


            button.setOnClickListener(new View.OnClickListener(){



                public void onClick(View view) {
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            LoginTask simpleTask = new LoginTask();
                            String asd = "asd";
                            simpleTask.execute("asd");
                        }
                    });
                }
            });
        }


       private class LoginTask extends AsyncTask<String, Void, String>
       {
            protected String doInBackground(String...voids)
            {


                String url="https://10.8.58.3:8080";
                StringRequest request=new StringRequest(Request.Method.POST,url,new Response.Listener<String>(){

                @Override
                public void onResponse(String response){

                           if (!response.equals(null)) {
                                textView.setText("Response is: " + response.intern());

                            }
                           else {
                                textView.setText("Response is " + response.intern());

                            }
                }
                },new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                           textView.setText("İnterneti aç.");

                    }

                    })
                {
                    @Override
                    public Map<String, String> getHeaders()throws AuthFailureError{
                        Map<String, String> params=new HashMap<String, String>();
                        params.put("User", username.getText().toString());
                        params.put("Pass", password.getText().toString());
                        return params;

                    }

                    /*
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("User", "Furkan");
                        params.put("Pass", "admin");
                        return params;
                    }*/
                };

                RequestQueue queue=Volley.newRequestQueue(getApplicationContext());
                queue.add(request);

                return null;
            }

       }
}

