package com.example.spodia;

import androidx.appcompat.app.AppCompatActivity;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

public class MainActivity extends AppCompatActivity {

    AccountManager accountManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accountManager = AccountManager.get(getApplicationContext());

        if(accountManager.getAccounts().length!=0)
        {
            Intent mainPage = new Intent(MainActivity.this, MainPage.class);
            startActivity(mainPage);
            finish();
        }

        else
        {
            Intent login = new Intent(MainActivity.this, AuthenticatorActivity.class);
            startActivity(login);
            finish();
        }
    }

}