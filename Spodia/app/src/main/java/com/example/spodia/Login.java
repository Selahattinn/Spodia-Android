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


public class Login extends Activity
{

    /**
     * et = EditText - cb = CheckBox - tv = TextView - iv = ImageView
     */
    /**private EditText et_username;
    private EditText et_password;
    private Button buttonLogin;
    private CheckBox cb_rememberMe;
    private TextView tv_ForgetPassword;
    private ImageView iv_FacebookLogin;
    private ImageView iv_TwitterLogin;
    private ImageView iv_GmailLogin;
    private Button buttonSignUp;


    @Override
    protected void onCreate(final Bundle savedInstanceState)
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
                finish();
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

*/

    }


