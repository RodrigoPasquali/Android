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
import com.example.listexampleapi.R;

public class ArticleActivity extends AppCompatActivity {
    private Article item;
    private TextView tvTitle;
    private TextView tvPrice;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        getViewLayouts();

        getPostItem();

        setDataView();
    }

    private void getPostItem(){
        Bundle extras = getIntent().getExtras();
        String stringTitle = extras.getString("title");
        String stringPrice = extras.getString("price");
        String stringImage = extras.getString("image");

        item = new Article();
        item.setTitle(stringTitle);
        item.setPrice(stringPrice);
        item.setImage(stringImage);
    }

    private void getViewLayouts(){
        tvTitle = findViewById(R.id.title);
        tvPrice = findViewById(R.id.price);
        image = findViewById(R.id.imageArticle);
    }

    private void setDataView(){
        tvTitle.setText(item.getTitle());
        tvPrice.setText(item.getPrice());

        ImageRequest request = new ImageRequest(item.getImage(),
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
