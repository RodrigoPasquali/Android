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

public class SplashActivity extends AppCompatActivity {
    private TextView tvVersion;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getViewsLayout();

        tvVersion.setText("V" + BuildConfig.VERSION_NAME);

        onScreenTouch();
    }

    private void onScreenTouch() {
        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ArticleListActivity.class));
                overridePendingTransition(0, 0);
                SplashActivity.this.finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }

    private void getViewsLayout() {
        tvVersion = findViewById(R.id.tvVersion);
    }
}
