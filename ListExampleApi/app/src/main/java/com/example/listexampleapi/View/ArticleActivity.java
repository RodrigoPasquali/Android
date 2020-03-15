package com.example.listexampleapi.View;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.example.listexampleapi.Model.Article;
import com.example.listexampleapi.Presenter.ArticleActivityPresenter;
import com.example.listexampleapi.R;

public class ArticleActivity extends AppCompatActivity {
    private Article article;
    private ArticleActivityPresenter articleActivityPresenter;
    private TextView tvTitle;
    private TextView tvPrice;
    private TextView tvCondition;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        this.articleActivityPresenter = new ArticleActivityPresenter(this);
        getViewLayouts();

        getPostItem();

        setDataView();
    }

    private void getPostItem(){
        Bundle extras = getIntent().getExtras();
        String articleId = extras.getString("id");
        String stringTitle = extras.getString("title");
        String stringPrice = extras.getString("price");
        String stringImage = extras.getString("image");
        String stringCondition = extras.getString("condition");

        this.article = new Article(articleId);
//        article.setTitle(stringTitle);
//        article.setPrice(stringPrice);
//        article.setUrlImage(stringImage);
//        article.setCondition(stringCondition);

        this.articleActivityPresenter.getArticleData(this.article);

        String s = "s";
    }

    private void getViewLayouts(){
        this.tvTitle = findViewById(R.id.tvTitle);
        this.tvPrice = findViewById(R.id.tvPrice);
        this. tvCondition = findViewById(R.id.tvCondition);
        this.image = findViewById(R.id.imageArticle);
    }

    private void setDataView(){
        this.tvTitle.setText(this.article.getTitle());
        this.tvPrice.setText(this.article.getPrice());
        this.tvCondition.setText(this.article.getCondition());

        ImageRequest request = new ImageRequest(this.article.getUrlImage(),
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        image.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        image.setImageResource(R.drawable.error);
                    }
                });
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);

    }
}
