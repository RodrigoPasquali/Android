package com.example.listexampleapi.View;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.listexampleapi.Model.Article;
import com.example.listexampleapi.Presenter.MainActivityPresenter;
import com.example.listexampleapi.R;
import com.example.listexampleapi.View.ViewUtils.ArticleAdapter;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArticleAdapter adapter;
    private EditText edSearch;
    private ImageView btnSearch;

    private MainActivityPresenter mainActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getLayoutViews();

        initButtonPressureEffect();

        adapter = new ArticleAdapter(this);

        this.mainActivityPresenter = new MainActivityPresenter(getApplicationContext());

        onItemTouch();

        onSearchActionTouch();
    }

    private void initButtonPressureEffect() {


    }

    private void getLayoutViews() {
        listView = findViewById(R.id.listView);
        edSearch = findViewById(R.id.edSearch);
        btnSearch = findViewById(R.id.btnSearch);
    }

    private void generateListView(){
        String article = edSearch.getText().toString();

        if(article.equals("")) {
            Toast.makeText(MainActivity.this,
                    getResources().getString(R.string.please_enter_article_searched),
                    Toast.LENGTH_LONG).show();
        } else {
            mainActivityPresenter.getArticleList(article, adapter);
            adapter.notifyDataSetChanged();
            listView.setAdapter(adapter);

            Toast.makeText(MainActivity.this,
                    getResources().getString(R.string.searcher) + edSearch.getText(),
                    Toast.LENGTH_LONG).show();
        }
    }

    private void onSearchActionTouch(){
        edSearch.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                boolean boolReturn = false;

                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if(keyCode == KeyEvent.KEYCODE_ENTER){
                        generateListView();
                        boolReturn = true;
                    }
                }
                return boolReturn;
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateListView();
            }
        });
    }

    private void onItemTouch(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Article listItem = adapter.getItem(position);
                Intent intent = new Intent(MainActivity.this, ArticleActivity.class);
                intent.putExtra("title", listItem.getTitle());
                intent.putExtra("price", listItem.getPrice());
                intent.putExtra("image", listItem.getUrlImage());
                startActivity(intent);
            }
        });
    }

}
