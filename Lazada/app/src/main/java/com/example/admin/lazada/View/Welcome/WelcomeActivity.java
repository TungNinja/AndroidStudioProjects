package com.example.admin.lazada.View.Welcome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.lazada.R;
import com.example.admin.lazada.View.Home.HomeActivity;

/**
 * Created by ADMIN on 11/30/2017.
 */

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weblcome_layout);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    Intent iHome = new Intent(WelcomeActivity.this, HomeActivity.class );
                    startActivity(iHome);
                }

            }
        });

        thread.start();
    }

}
