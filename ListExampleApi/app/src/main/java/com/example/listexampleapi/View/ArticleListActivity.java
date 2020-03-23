package com.example.listexampleapi.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.listexampleapi.Model.Article;
import com.example.listexampleapi.Presenter.ArticleListPresenter;
import com.example.listexampleapi.Presenter.Interface.ArticleListPresenterInterface;
import com.example.listexampleapi.R;
import com.example.listexampleapi.View.Interface.ArticleListView;
import com.example.listexampleapi.View.ViewUtil.ArticleAdapter;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class ArticleListActivity extends AppCompatActivity implements ArticleListView,
        NavigationView.OnNavigationItemSelectedListener,
        DrawerLayout.DrawerListener{
    private ArticleListPresenterInterface presenter;

    private ListView listView;
    private ArticleAdapter adapter;
    private EditText edSearch;
    private ImageView btnSearch;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ImageView ivMenu;
    private NavigationView navigationView;

    boolean doubleBackToExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);

        getViewsLayout();

        this.adapter = new ArticleAdapter(this);

        this.presenter = new ArticleListPresenter(getApplicationContext(), this);

        onItemTouch();

        onSearchActionTouch();

        initToolbar();

        onMenuItemClick();

        initNavigationView();
    }

    private void getViewsLayout() {
        this.listView = findViewById(R.id.listView);
        this.edSearch = findViewById(R.id.edSearch);
        this.btnSearch = findViewById(R.id.btnSearch);
        this.ivMenu = findViewById(R.id.ivMenu);
        this.toolbar = findViewById(R.id.action_bar);
        this.drawerLayout = findViewById(R.id.drawer_layout);
        this.navigationView = findViewById(R.id.nav_view);
    }

    private void initToolbar(){
        Toolbar toolbar = findViewById(R.id.action_bar);
        if (toolbar!= null){
            toolbar.setTitleTextColor(Color.BLACK);
        }
    }

    private void initNavigationView(){
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        drawerLayout.addDrawerListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about: {
                Intent intent = new Intent(ArticleListActivity.this, SplashActivity.class);

                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            }

            case R.id.exit: {
                finishAffinity();
            }

            default:
                break;
        }

        return true;
    }

    private void onMenuItemClick() {
        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.END); //Edit Gravity.START need API 14
            }
        });
    }

    private boolean checkInternet(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        boolean connectionInternet = true;

        if (networkInfo != null && networkInfo.isConnected()) {
            connectionInternet = true;
        } else {
            connectionInternet = false;
        }

        return connectionInternet;
    }

    private void onSearchActionTouch(){
        this.edSearch.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                boolean boolReturn = false;

                if(checkInternet()) {
                    if (event.getAction() == KeyEvent.ACTION_DOWN) {
                        if(keyCode == KeyEvent.KEYCODE_ENTER){
                            generateListView();
                            boolReturn = true;
                        }
                    }
                } else {
                    Toast.makeText(ArticleListActivity.this,
                            getResources().getString(R.string.not_internet),
                            Toast.LENGTH_LONG).show();
                }

                return boolReturn;
            }
        });

        this.btnSearch.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                animateSearchImage(500);

                if(checkInternet()) {
                    generateListView();
                } else {
                    Toast.makeText(ArticleListActivity.this,
                            getResources().getString(R.string.not_internet),
                            Toast.LENGTH_LONG).show();
                }
            }

        });
    }

    private void generateListView(){
        String article = this.edSearch.getText().toString();

        if(article.equals("")) {
            Toast.makeText(ArticleListActivity.this,
                    getResources().getString(R.string.please_enter_article_searched),
                    Toast.LENGTH_LONG).show();
        } else {
            this.presenter.getArticleList(article);

            Toast.makeText(ArticleListActivity.this,
                    getResources().getString(R.string.searcher) + this.edSearch.getText(),
                    Toast.LENGTH_LONG).show();
        }
    }

    private void onItemTouch(){
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(checkInternet()) {
                    Article item = adapter.getItem(position);
                    Intent intent = new Intent(ArticleListActivity.this, ArticleActivity.class);
                    intent.putExtra("id", item.getId());

                    startActivity(intent);
                    overridePendingTransition(0, 0);
                } else {
                    Toast.makeText(ArticleListActivity.this,
                            getResources().getString(R.string.not_internet),
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void animateSearchImage(int duration) {
        int colorFrom = Color.GRAY;
        int colorTo = Color.WHITE;
        ObjectAnimator.ofObject(btnSearch, "backgroundColor", new ArgbEvaluator(), colorFrom, colorTo)
                .setDuration(duration)
                .start();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawer(GravityCompat.END);
        } else {
            if (doubleBackToExit) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExit = true;
            Toast.makeText(this, getResources().getString(R.string.exit_app), Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExit = false;
                }
            }, 2000);
        }
    }

    @Override
    public void showResult(List<Article> articleList) {
        if(articleList.size() == 0){
            adapter.clear();
            adapter.setItems(null);
            listView.setAdapter(null);
            Toast.makeText(ArticleListActivity.this,
                    getResources().getString(R.string.no_items_available) + " " + this.edSearch.getText(),
                    Toast.LENGTH_LONG).show();
        } else {
            this.adapter.setItems(articleList);
            this.listView.setAdapter(this.adapter);
        }
    }

    @Override
    public void invalidOperation() {
        Toast.makeText(ArticleListActivity.this,
                getResources().getString(R.string.searcher) + " " + this.edSearch.getText(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerClosed(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }
}
