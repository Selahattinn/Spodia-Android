package com.example.furkan_arif_signup;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView helloTextView = (TextView) findViewById(R.id.textView);


        String uri ="http://127.0.0.1:8000/login";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(uri)
                .header("name", "selahattin")
                .header("password", "asdqwezxc")
                .build();

        helloTextView.setText("request oluşturuldu");

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                TextView helloTextView2 = (TextView) findViewById(R.id.textView);
                if (response.isSuccessful()) {

                    helloTextView2.setText("response oluşturuldu");
                    }
                else
                {
                    helloTextView2.setText("response oluşturulamadı");
                }
                        /*request.headers().;
                        final String myResponse = response.body().string();
                        System.out.println(response.headers().get);
                        System.out.println("a")*/


            }
        });

    }
}






