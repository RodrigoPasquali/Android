package com.example.listexampleapi.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.listexampleapi.Model.Article;
import com.example.listexampleapi.Presenter.ArticlePresenter;
import com.example.listexampleapi.R;
import com.example.listexampleapi.View.Interface.ArticleView;
import com.example.listexampleapi.View.ViewUtil.ImageViewRisizer;

import java.util.List;

public class ArticleActivity extends AppCompatActivity implements ArticleView {
    private ArticlePresenter presenter;
    private ImageViewRisizer imageViewRisizer;

    private TextView tvTitle;
    private TextView tvPrice;
    private TextView tvCondition;
    private TextView tvAvailableQuantity;
    private TextView tvSoldQuantity;
//    private TextView tvSellerAddress;
    private ImageView image;
    private Button btnLink;

    private ImageSwitcher imageSwitcher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        this.presenter = new ArticlePresenter(getApplicationContext(), this);
        this.imageViewRisizer = new ImageViewRisizer(getApplicationContext());

        getViewLayouts();

        getArticleSelected();

        initImageSwitcher();
    }

    private void initImageSwitcher(){
        this.imageSwitcher = findViewById(R.id.imageArticle);
        this.imageSwitcher.setFactory(new ViewSwitcher.ViewFactory()
        {
            public View makeView()
            {
                ImageView imageView = new ImageView(ArticleActivity.this);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));

                return imageView;
            }
        });

//        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
//        Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
//        imageSwitcher.setInAnimation(fadeIn);
//        imageSwitcher.setOutAnimation(fadeOut);

        Animation in = AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left); // load an animation
        this.imageSwitcher.setInAnimation(in); // set in Animation for ImageSwitcher
        Animation out = AnimationUtils.loadAnimation(this,android.R.anim.slide_out_right); // load an animation
        this.imageSwitcher.setOutAnimation(out); // set out Animation for ImageSwitcher
    }

    int currentImagePosition = -1;

    private void imageSwitcherOnTouch(final List<Bitmap> bitmapList) {
        this.imageSwitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                currentImagePosition++;
                //  Check If index reaches maximum then reset it
                if (currentImagePosition == bitmapList.size()){
                    currentImagePosition = 0;
                }

//                Drawable drawable = bitmapList.get(currentImagePosition);

                Drawable drawable = new BitmapDrawable(getResources(), bitmapList.get(currentImagePosition));

                imageSwitcher.setImageDrawable(drawable); // set the image in ImageSwitcher
            }
        });
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
//        this.image = findViewById(R.id.imageArticle);
        this.tvAvailableQuantity = findViewById(R.id.tvAvailableQuantity);
        this.tvSoldQuantity = findViewById(R.id.tvSoldQuantity);
//        this.tvSellerAddress = findViewById(R.id.tvSellerAddress);
        this.btnLink = findViewById(R.id.btnLink);
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
//        this.tvSellerAddress.setText(pArticle.);


        if(pArticle.getImages().size() != 0){
            imageSwitcherOnTouch(pArticle.getImages());
        }

//
//        if(pArticle.getImage() == null){
//            image.setImageBitmap(BitmapFactory.decodeResource(getApplicationContext().getResources(),
//                    R.drawable.error));
//        } else {
//            image.setImageBitmap(pArticle.getImage());
//
//            int width = getWidthDisplaySizes();
//            this.imageViewRisizer.scaleImage(image, width);
//        }


        btnLinkAction(pArticle.getLinkToPublication());
    }

    @Override
    public void invalidOperation() {

    }
}
