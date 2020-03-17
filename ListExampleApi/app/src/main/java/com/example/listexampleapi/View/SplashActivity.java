package com.example.listexampleapi.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.listexampleapi.BuildConfig;
import com.example.listexampleapi.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    private TextView tvVersion;
    private ConstraintLayout constraintLayout;
    private int timeOut = 3000;
    private boolean isTouchedScreen = false;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getViewsLayout();

        tvVersion.setText(BuildConfig.VERSION_NAME);

        onScreenTouchAction();

        if(isTouchedScreen) {
            initTimeOut(timeOut);
        }
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

    public void onScreenTouchAction(){
        constraintLayout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isTouchedScreen = true;
                startActivity(new Intent(getApplicationContext(), ArticleListActivity.class));
                SplashActivity.this.finish();

            }
        });
    }
}
