package com.example.listexampleapi.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.KeyEvent;
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

import java.util.List;

public class ArticleListActivity extends AppCompatActivity implements ArticleListView {
    private ListView listView;
    private ArticleAdapter adapter;
    private EditText edSearch;
    private ImageView btnSearch;

    private ArticleListPresenterInterface presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);

        getLayoutViews();

        this.adapter = new ArticleAdapter(this);

        this.presenter = new ArticleListPresenter(getApplicationContext(), this);

        onItemTouch();

        onSearchActionTouch();
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

    private void getLayoutViews() {
        this.listView = findViewById(R.id.listView);
        this.edSearch = findViewById(R.id.edSearch);
        this.btnSearch = findViewById(R.id.btnSearch);
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
            @Override
            public void onClick(View v) {
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

    private void onItemTouch(){
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(checkInternet()) {
                    Article item = adapter.getItem(position);
                    Intent intent = new Intent(ArticleListActivity.this, ArticleActivity.class);
                    intent.putExtra("id", item.getId());

                    startActivity(intent);
                } else {
                    Toast.makeText(ArticleListActivity.this,
                            getResources().getString(R.string.not_internet),
                            Toast.LENGTH_LONG).show();
                }
            }
        });
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
}
