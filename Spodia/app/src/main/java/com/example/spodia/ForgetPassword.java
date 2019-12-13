package com.example.spodia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ForgetPassword extends AppCompatActivity {
    private EditText name;
    private Button resetButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        resetButton =findViewById(R.id.resetButton);

        name =findViewById(R.id.userNameReset);
        resetButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent resetPasswordIntent = new Intent(ForgetPassword.this,ResetPassword.class);
                 startActivity(resetPasswordIntent);
             }
         });
    }
}
