package com.example.listexampleapi.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.listexampleapi.Model.Article;
import com.example.listexampleapi.Presenter.ArticlePresenter;
import com.example.listexampleapi.R;
import com.example.listexampleapi.View.Interface.ArticleView;
import com.example.listexampleapi.View.ViewUtil.ImageViewRisizer;

public class ArticleActivity extends AppCompatActivity implements ArticleView {
    private ArticlePresenter presenter;
    private ImageViewRisizer imageViewRisizer;

    private TextView tvTitle;
    private TextView tvPrice;
    private TextView tvCondition;
    private TextView tvAvailableQuantity;
    private TextView tvSoldQuantity;
    private ImageView image;
    private Button btnLink;
    private ImageView ivMercadoPago;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        this.presenter = new ArticlePresenter(getApplicationContext(), this);
        this.imageViewRisizer = new ImageViewRisizer(getApplicationContext());

        getViewsLayout();

        getArticleSelected();

        initToolbar();
    }

    private void initToolbar(){
        Toolbar toolbar = findViewById(R.id.action_bar);
        if (toolbar!= null){
            toolbar.setTitleTextColor(Color.BLACK);
        }
    }

    private void getViewsLayout(){
        this.tvTitle = findViewById(R.id.tvTitle);
        this.tvPrice = findViewById(R.id.tvPrice);
        this.tvCondition = findViewById(R.id.tvCondition);
        this.image = findViewById(R.id.imageArticle);
        this.tvAvailableQuantity = findViewById(R.id.tvAvailableQuantity);
        this.tvSoldQuantity = findViewById(R.id.tvSoldQuantity);
        this.btnLink = findViewById(R.id.btnLink);
        this.ivMercadoPago = findViewById(R.id.ivMercadoPago);
    }

    private void getArticleSelected(){
        Bundle extras = getIntent().getExtras();
        String articleId = extras.getString("id");

        this.presenter.getArticleData(articleId);
    }

    private int getWidthDisplaySizes(){
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    private void btnLinkAction(final String url){
        btnLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (url != null && !url.isEmpty()){
                    Intent intentWeb = new Intent();
                    intentWeb.setAction(Intent.ACTION_VIEW);
                    intentWeb.setData(Uri.parse(url));
                    startActivity(intentWeb);
                }
            }
        });
    }

    @Override
    public void showResult(Article pArticle) {
        this.tvTitle.setText(pArticle.getTitle());
        this.tvPrice.setText(pArticle.getPrice());

        switch (pArticle.getCondition()){
            case "new":
                this.tvCondition.setText(getResources().getString(R.string.new_condition));

                break;

            case "used":
                this.tvCondition.setText(getResources().getString(R.string.used_condition));

                break;

            default:
                this.tvCondition.setText("-");
                break;
        }
        this.tvAvailableQuantity.setText(pArticle.getAvailableQuantity());
        this.tvSoldQuantity.setText(pArticle.getSoldQuantity());

        if(pArticle.getImage() == null){
            image.setImageBitmap(BitmapFactory.decodeResource(getApplicationContext().getResources(),
                    R.drawable.error));
        } else {
            image.setImageBitmap(pArticle.getImage());

            int width = getWidthDisplaySizes();
            this.imageViewRisizer.scaleImage(image, width);
        }

        if(pArticle.isMercadoPagoAccept()){
            ivMercadoPago.setImageResource(R.drawable.accept);
        } else {
            ivMercadoPago.setImageResource(R.drawable.not_accept);
        }

        btnLinkAction(pArticle.getLinkToPublication());
    }

    @Override
    public void invalidOperation() {

    }
}
