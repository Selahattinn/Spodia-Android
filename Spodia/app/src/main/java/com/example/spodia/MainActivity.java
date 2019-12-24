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
    public TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text =findViewById(R.id.text);
        new NukeSSLCerts().nuke();

       accountManager = AccountManager.get(getApplicationContext());
        if(accountManager.getAccounts().length!=0){
            text.setText(accountManager.getAccounts()[0].name);
            Button button = findViewById(R.id.button2);
            button.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                        accountManager.removeAccountExplicitly(accountManager.getAccounts()[0]);
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

        else {
            Intent login = new Intent(MainActivity.this, Login.class);
            startActivity(login);
            finish();
        }


    }

    // TOKENLİ İSTEK İÇİN :
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
                            Toast toast = Toast.makeText(getApplicationContext(), "Succesfull.", Toast.LENGTH_SHORT);
                            toast.show();
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