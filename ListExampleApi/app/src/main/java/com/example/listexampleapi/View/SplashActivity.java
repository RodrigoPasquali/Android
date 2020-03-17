package com.example.listexampleapi.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.example.listexampleapi.BuildConfig;
import com.example.listexampleapi.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    private TextView tvVersion;
    private ConstraintLayout constraintLayout;
    private int timeOut = 3000;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getViewsLayout();

        tvVersion.setText("V" + BuildConfig.VERSION_NAME);

        initTimeOut(timeOut);
    }

    private void getViewsLayout() {
        tvVersion = findViewById(R.id.tvVersion);
        constraintLayout = findViewById(R.id.constraintLayout);
    }

    private void initTimeOut(int time){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), ArticleListActivity.class));

                SplashActivity.this.finish();
            }
        }, time);
    }
}
