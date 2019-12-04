package com.example.spodia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    private TextView tvName;
    private TextView tvPassword;
    private Button buttonLogin;
    private CheckBox checkBox;
    private TextView tvForgetPassword;
    private ImageView ıvFacebookLogin;
    private ImageView ıvTwitterLogin;
    private ImageView ıvGmailLogin;
    private Button buttonSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tvName =findViewById(R.id.name);
        tvPassword=findViewById(R.id.password);
        buttonLogin=findViewById(R.id.Login);
        checkBox=findViewById(R.id.rememberMe);
        tvForgetPassword=findViewById(R.id.forgetPassword);
        ıvFacebookLogin=findViewById(R.id.facebook);
        ıvTwitterLogin=findViewById(R.id.twitter);
        ıvGmailLogin=findViewById(R.id.gmail);
        buttonSignIn=findViewById(R.id.signIn);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainPage =new Intent(Login.this,MainPage.class);
                startActivity(mainPage);
            }
        });
        tvForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgetPassword =new Intent(Login.this,ForgetPassword.class);
                startActivity(forgetPassword);
            }
        });
        ıvFacebookLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent facebookLogin =new Intent(Login.this,FacebookLogin.class);
                startActivity(facebookLogin);
            }
        });
        ıvTwitterLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signIn =new Intent(Login.this,SignIn.class);
                startActivity(signIn);
            }
        });
    }
}
