package com.example.listexampleapi.Presenter;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.listexampleapi.Model.Article;
import com.example.listexampleapi.Presenter.Interface.ArticlePresenterInterface;
import com.example.listexampleapi.Service.ImageVolleyCallback;
import com.example.listexampleapi.Service.JsonVolleyCallback;
import com.example.listexampleapi.Service.Parse.ListArticleParse;
import com.example.listexampleapi.Service.RequestService;
import com.example.listexampleapi.View.Interface.ArticleView;

import org.json.JSONException;
import org.json.JSONObject;

public class ArticlePresenter implements ArticlePresenterInterface {
    private RequestService requestService;
    private ListArticleParse listArticleParse;

    private ArticleView view;
    private Article article;

    public ArticlePresenter(Context context, ArticleView view) {
        this.requestService = RequestService.getInstance(context);
        this.listArticleParse = new ListArticleParse();
        this.view = view;
    }

    public void getArticleData(final String idArticle){
        this.requestService.getArticle(idArticle, new JsonVolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) throws JSONException {
                article = new Article(idArticle);
                listArticleParse.parseArticle(result, article);

//                getImage(article);

//                getImages(article);

                for(int i = 0; i < article.getUrlImages().size(); i++) {
                    getImage(article);
                }

                showResult(article);
            }

            @Override
            public void onError(String result) throws Exception {

            }
        });
    }

    private void getImage(final Article article){
        this.requestService.getImage(article.getUrlImage(), new ImageVolleyCallback() {
            @Override
            public void onImageSuccess(Bitmap bitmap) {
//                article.setImage(bitmap);
                article.addImage(bitmap);
//                showResult(article);
            }

            @Override
            public void onImageFail() {

            }
        });
    }

    private void getImages(final Article article){
        String url = null;

        for(int i = 0; i < article.getUrlImages().size(); i++) {
            url = article.getUrlImages().get(i);
//        }
            this.requestService.getImage(url, new ImageVolleyCallback() {
                @Override
                public void onImageSuccess(Bitmap bitmap) {
                    article.addImage(bitmap);
                }

                @Override
                public void onImageFail() {

                }
            });
        }
    }

    @Override
    public void showResult(Article article) {
        if(this.view != null){
            this.view.showResult(article);
        }
    }

    @Override
    public void invalidOperation() {

    }

    @Override
    public void getArticle(String articleId) {

    }
}
