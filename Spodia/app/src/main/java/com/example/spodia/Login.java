package com.example.spodia;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;



public class Login extends Activity {
    private TextView tvName;
    private TextView tvPassword;
    private Button buttonLogin;
    private CheckBox checkBox;
    private TextView tvForgetPassword;
    private ImageView ıvFacebookLogin;
    private ImageView ıvTwitterLogin;
    private ImageView ıvGmailLogin;
    private Button buttonSignUp;
    Toast toast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        new NukeSSLCerts().nuke();
        tvName =findViewById(R.id.name);
        tvPassword=findViewById(R.id.password);
        buttonLogin=findViewById(R.id.Login);
        checkBox=findViewById(R.id.rememberMe);
        tvForgetPassword=findViewById(R.id.forgetPassword);
        ıvFacebookLogin=findViewById(R.id.facebook);
        ıvTwitterLogin=findViewById(R.id.twitter);
        ıvGmailLogin=findViewById(R.id.gmail);
        buttonSignUp=findViewById(R.id.signUp);


        buttonLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                LoginTask simpleTask = new LoginTask();
                simpleTask.execute("trying to login");

                if(simpleTask.responseString != null)
                {
                    if (simpleTask.responseString.equals("Connected"))
                    {
                        toast.makeText(getApplicationContext(), "Login is succesfull.", Toast.LENGTH_SHORT);
                        toast.show();
                        Intent mainPage = new Intent(Login.this, MainPage.class);
                        startActivity(mainPage);
                    }
                    else
                        {
                        toast.makeText(getApplicationContext(),"Invalid login,try again.",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }

                else
                    {
                    toast.makeText(getApplicationContext(),"Control the internet connection!",Toast.LENGTH_SHORT);
                    toast.show();
                }
        }});

        tvForgetPassword.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent forgetPassword =new Intent(Login.this,ForgetPassword.class);
                startActivity(forgetPassword);
            }
        });

        ıvFacebookLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent facebookLogin =new Intent(Login.this,FacebookLogin.class);
                startActivity(facebookLogin);
            }
        });

        ıvTwitterLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent twitterLogin =new Intent(Login.this,TwitterLogin.class);
                startActivity(twitterLogin);
            }
        });

        ıvGmailLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gmailLogin =new Intent(Login.this,GmailLogin.class);
                startActivity(gmailLogin);
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent signIn =new Intent(Login.this,SignIn.class);
                startActivity(signIn);
            }
        });
    }



    private class LoginTask extends AsyncTask<String, Void, String>
    {
        String responseString = null;
        protected String doInBackground(String...voids)
        {


            String url="https://10.8.58.3:8080";
            StringRequest request=new StringRequest(Request.Method.POST,url,new Response.Listener<String>(){

                @Override
                public void onResponse(String response){

                    if (!response.equals(null)) {
                        responseString = response.toString();
                    }
                    else {
                        responseString = response.toString();

                    }
                }
            },new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error){
                    Log.d(responseString,"hata");
                }

            })
            {
                @Override
                public Map<String, String> getHeaders()throws AuthFailureError{
                    Map<String, String> params=new HashMap<String, String>();
                    params.put("User", tvName.getText().toString());
                    params.put("Pass", tvPassword.getText().toString());
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

            return responseString;
        }

    }
}
