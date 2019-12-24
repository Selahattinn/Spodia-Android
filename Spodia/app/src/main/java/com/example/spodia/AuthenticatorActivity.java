package com.example.spodia;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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


/**
 * The Authenticator activity.
 *
 * Called by the Authenticator and in charge of identifing the user.
 *
 * It sends back to the Authenticator the result.
 */
public class AuthenticatorActivity extends AccountAuthenticatorActivity {


    public final static String ARG_ACCOUNT_TYPE = "ACCOUNT_TYPE";
    public final static String ARG_AUTH_TYPE = "AUTH_TYPE";
    public final static String ARG_ACCOUNT_NAME = "ACCOUNT_NAME";
    public final static String ARG_IS_ADDING_NEW_ACCOUNT = "IS_ADDING_ACCOUNT";
    public static final String KEY_ERROR_MESSAGE = "ERR_MSG";
    public final static String PARAM_USER_PASS = "USER_PASS";
    private final int REQ_SIGNUP = 1;
    private final String TAG = this.getClass().getSimpleName();

    public String token=null;
    public String username=null;
    public String password=null;
    private AccountManager mAccountManager;
    private String mAuthTokenType;

    public TextView textView;
    public TextView textView2;


    public AuthenticatorActivity(){

        super();
    }
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(final  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticator);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);


        username = getIntent().getStringExtra("name");
        password = getIntent().getStringExtra("pass");
        mAccountManager = AccountManager.get(getApplicationContext());
        String accountName = getIntent().getStringExtra(ARG_ACCOUNT_NAME);
        mAuthTokenType = getIntent().getStringExtra(ARG_AUTH_TYPE);

        if (mAuthTokenType == null)
            mAuthTokenType = AccountGeneral.AUTHTOKEN_TYPE_FULL_ACCESS;


        if (accountName != null) {
            ////
        }
        request();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // The sign up activity returned that the user has successfully created an account
        if (requestCode == REQ_SIGNUP && resultCode == RESULT_OK) {
            finishLogin(data);
        } else
            super.onActivityResult(requestCode, resultCode, data);
    }


    public String request()
    {

        Map<String, String> params = new HashMap<String, String>();
        params.put("Content-Type", "application/");
        params.put("name", username);
        params.put("parola", password);
        JSONObject obj = new JSONObject(params);

        final String url = "https://192.168.3.240:8080/login";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,obj,new Response.Listener<JSONObject>() {

            @Override

            public void onResponse(JSONObject response) {
                try {
                    if (response.keys().hasNext())
                    {
                        if (response.getInt("status")==1)
                        {
                            token = response.getString("token");
                            submit();
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
                textView2.setText("hatalı giriş");
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
        return token;
    }

    public void submit() {
        final String accountType = getIntent().getStringExtra(ARG_ACCOUNT_TYPE);
        new AsyncTask<String, Void, Intent>() {

            @Override
            protected Intent doInBackground(String... params) {

                Log.d("udinic", TAG + "> Started authenticating");


                Bundle data = new Bundle();
                try {
                    if(token == null) {
                        token = request();
                    }
                    else{
                        data.putString(AccountManager.KEY_ACCOUNT_NAME, username);
                        data.putString(AccountManager.KEY_ACCOUNT_TYPE, accountType);
                        data.putString(AccountManager.KEY_AUTHTOKEN, token);
                        data.putString(PARAM_USER_PASS, password);
                    }

                } catch (Exception e) {

                    data.putString(KEY_ERROR_MESSAGE, e.getMessage());

                }

                final Intent res = new Intent();

                res.putExtras(data);
                return res;

            }

            @Override
            protected void onPostExecute(Intent intent) {
                if (intent.hasExtra(KEY_ERROR_MESSAGE)) {
                    Toast toast = Toast.makeText(getBaseContext(), intent.getStringExtra(KEY_ERROR_MESSAGE), Toast.LENGTH_SHORT);
                    toast.show();
                } else {

                    finishLogin(intent);
                }
            }
        }.execute();
    }

    private void finishLogin(Intent intent) {
        Log.d("udinic", TAG + "> finishLogin");

        String accountName = intent.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
        String accountPassword = intent.getStringExtra(PARAM_USER_PASS);

        final Account account = new Account(accountName, "com.example.spodia"); // burası ayarlanacak. com.example.spodia yerine acoount type olacak.

        if (intent.getBooleanExtra(ARG_IS_ADDING_NEW_ACCOUNT, true)) {
            Log.d("udinic", TAG + "> finishLogin > addAccountExplicitly");
            String authtoken = intent.getStringExtra(AccountManager.KEY_AUTHTOKEN);
            String authtokenType = mAuthTokenType;

            // Creating the account on the device and setting the auth token we got
            // (Not setting the auth token will cause another call to the server to authenticate the user)
            mAccountManager.addAccountExplicitly(account, accountPassword, null);
            mAccountManager.setAuthToken(account, authtokenType, authtoken);
        } else {
            Log.d("udinic", TAG + "> finishLogin > setPassword");
            mAccountManager.setPassword(account, accountPassword);
        }

        setAccountAuthenticatorResult(intent.getExtras());
        setResult(RESULT_OK, intent);


        Intent mainPage = new Intent(getApplicationContext(), MainPage.class);
        startActivity(mainPage);

    }

}
