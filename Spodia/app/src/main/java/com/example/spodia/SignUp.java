package com.example.spodia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;


public class SignUp extends AppCompatActivity {
    private EditText et_username;
    private EditText et_name;
    private EditText et_surname;
    private EditText et_email;
    private EditText et_password;
    private EditText et_repeat_password;
    private CheckBox userAgrementbox;
    private CheckBox information;
    private ImageView userAgrement;
    private Button  register;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        et_email= findViewById(R.id.emailSignUp);
        et_name = findViewById(R.id.nameSignUp);
        et_password= findViewById(R.id.passwordSignUp);
        et_repeat_password= findViewById(R.id.repeatPasswordSignUp);
        et_surname=findViewById(R.id.surnameSignUp);
        et_username=findViewById(R.id.usernameSignUp);
        information=findViewById(R.id.informationSignUp);
        userAgrementbox= findViewById(R.id.userAgreementBoxSignUp);
        userAgrement = findViewById(R.id.userAgreementSignUp);
        register=findViewById(R.id.registerSignUp);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this,Register.class);
                startActivity(intent);
            }
        });
    }
}
