package com.example.listexampleapi.View;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.listexampleapi.Model.Article;
import com.example.listexampleapi.Presenter.ArticlePresenter;
import com.example.listexampleapi.R;
import com.example.listexampleapi.View.Interface.ArticleView;

public class ArticleActivity extends AppCompatActivity implements ArticleView {
    private ArticlePresenter presenter;
    private TextView tvTitle;
    private TextView tvPrice;
    private TextView tvCondition;
    private TextView tvAvailableQuantity;
    private TextView tvSoldQuantity;
//    private TextView tvSellerAddress;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        this.presenter = new ArticlePresenter(getApplicationContext(), this);

        getViewLayouts();

        getArticleSelected();
    }

    private void getArticleSelected(){
        Bundle extras = getIntent().getExtras();
        String articleId = extras.getString("id");

        this.presenter.getArticleData(articleId);
    }

    private void getViewLayouts(){
        this.tvTitle = findViewById(R.id.tvTitle);
        this.tvPrice = findViewById(R.id.tvPrice);
        this.tvCondition = findViewById(R.id.tvCondition);
        this.image = findViewById(R.id.imageArticle);
        this.tvAvailableQuantity = findViewById(R.id.tvAvailableQuantity);
        this.tvSoldQuantity = findViewById(R.id.tvSoldQuantity);
//        this.tvSellerAddress = findViewById(R.id.tvSellerAddress);
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
//        this.tvSellerAddress.setText(pArticle.);

        if(pArticle.getImage() == null){
            image.setImageBitmap(BitmapFactory.decodeResource(getApplicationContext().getResources(),
                    R.drawable.error));
        } else {
            image.setImageBitmap(pArticle.getImage());
        }
    }

    @Override
    public void invalidOperation() {

    }
}
