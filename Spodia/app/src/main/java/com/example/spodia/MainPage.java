package com.example.spodia;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
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
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MainPage extends AppCompatActivity  {

    AccountManager accountManager;
    public TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        new NukeSSLCerts().nuke();
        text= findViewById(R.id.textx);
        accountManager = AccountManager.get(getApplicationContext());

        Button button = findViewById(R.id.button2);
        text.setText(accountManager.getAccounts()[0].name);
        button.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                        accountManager.removeAccountExplicitly(accountManager.getAccounts()[0]);
                        Intent login = new Intent(MainPage.this, AuthenticatorActivity.class);
                        startActivity(login);
                        finish();
                    }
                }
            });

            Button button2 = findViewById(R.id.button3);
            button2.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    request();
                }
            });
        }


    public void request()
    {

        Map<String, String> params = new HashMap<String, String>();
        params.put("Content-Type", "application/");
        params.put("name", accountManager.getAccounts()[0].name);
        params.put("token", accountManager.peekAuthToken(accountManager.getAccounts()[0],AccountGeneral.AUTHTOKEN_TYPE_FULL_ACCESS));
        JSONObject obj = new JSONObject(params);
        final String url = "https://192.168.3.240:8080/loginWithToken";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,obj,new Response.Listener<JSONObject>() {

            @Override

            public void onResponse(JSONObject response) {
                try {
                    if (response.keys().hasNext())
                    {
                        if (response.getInt("status")==1)
                        {

                        }
                        else
                        {

                        }
                    }
                    else
                    {

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

                return params;
            }

            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/");

                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }



}

