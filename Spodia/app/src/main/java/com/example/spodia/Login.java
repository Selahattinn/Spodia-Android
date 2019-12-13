package com.example.spodia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;



public class Login extends Activity
{
    /**
     * et = EditText - cb = CheckBox - tv = TextView - iv = ImageView
     */
    private EditText et_username;
    private EditText et_password;
    private Button buttonLogin;
    private CheckBox cb_rememberMe;
    private TextView tv_ForgetPassword;
    private ImageView iv_FacebookLogin;
    private ImageView iv_TwitterLogin;
    private ImageView iv_GmailLogin;
    private Button buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        new NukeSSLCerts().nuke();

        et_username = findViewById(R.id.username);
        et_password = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.Login);
        cb_rememberMe = findViewById(R.id.rememberMe);
        tv_ForgetPassword = findViewById(R.id.forgetPassword);
        iv_FacebookLogin = findViewById(R.id.facebook);
        iv_TwitterLogin = findViewById(R.id.twitter);
        iv_GmailLogin = findViewById(R.id.gmail);
        buttonSignUp = findViewById(R.id.signUp);


        buttonLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                LoginTask loginTask = new LoginTask();
                loginTask.execute("");
            }
        });

        tv_ForgetPassword.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent forgetPassword = new Intent(Login.this, ForgetPassword.class);
                startActivity(forgetPassword);
            }
        });

        iv_FacebookLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent facebookLogin = new Intent(Login.this, FacebookLogin.class);
                startActivity(facebookLogin);
            }
        });

        iv_TwitterLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent twitterLogin = new Intent(Login.this, TwitterLogin.class);
                startActivity(twitterLogin);
            }
        });

        iv_GmailLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent gmailLogin = new Intent(Login.this, GmailLogin.class);
                startActivity(gmailLogin);
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent signIn = new Intent(Login.this, SignUp.class);
                startActivity(signIn);
            }
        });
    }


    private class LoginTask extends AsyncTask<String, Void, String>
    {

        protected String doInBackground(String... voids)
        {


            final String url = "https://10.1.243.110:8080";
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,null,new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                     try {
                        if (response.keys().hasNext())
                        {
                            if (response.getString("name").equals(et_username.getText().toString()))
                            {
                                Toast toast = Toast.makeText(getApplicationContext(), "Succesfull", Toast.LENGTH_SHORT);
                                toast.show();
                                Intent mainPage = new Intent(Login.this, MainPage.class);
                                startActivity(mainPage);
                            }
                            else
                            {
                                Toast toast = Toast.makeText(getApplicationContext(), "Invalid login.", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }
                        else
                        {
                            Toast toast = Toast.makeText(getApplicationContext(), "Connection poroblem.", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    } catch ( JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error)
                {

                }

            })
            {
                @Override
                public Map<String,String> getHeaders() throws AuthFailureError
                {

                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Content-Type", "application/");
                    params.put("User", et_username.getText().toString());
                    params.put("Pass", et_password.getText().toString());

                    return params;
                }

                @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("User", "Furkan");
                    params.put("Pass", "admin");
                    return params;
                }
            };

            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            queue.add(request);
           return null;
        }
    }
}

