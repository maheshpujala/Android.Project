package com.example.maheshpujala.onlinestorefragment.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.example.maheshpujala.onlinestorefragment.R;
import com.example.maheshpujala.onlinestorefragment.api.NetworkCheck;

/**
 * Created by maheshpujala on 10/6/16.
 */
public class SplashActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private CoordinatorLayout container;
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // To make activity full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_splash);

        container   = (CoordinatorLayout) findViewById(R.id.activity_splash_container);
        progressBar = (ProgressBar)       findViewById(R.id.activity_splash_pb);
        checkConnection();

    }

    private void checkConnection() {
        if (NetworkCheck.isInternetAvailable(SplashActivity.this))  //if connection available
        {
            loadAndParseConfig();

        } else {

            Snackbar.make(container, "No Internet Connection", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Refresh", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            checkConnection();
                        }
                    }).show();
        }
    }
    private void loadAndParseConfig() {
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent start = new Intent(SplashActivity.this,HomeActivity.class);
                startActivity(start);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}
