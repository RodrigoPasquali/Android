package com.example.listexampleapi.Presenter;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.listexampleapi.Model.Article;
import com.example.listexampleapi.Services.ImageVolleyCallback;
import com.example.listexampleapi.Services.Parse.ListArticleParse;
import com.example.listexampleapi.Services.RequestService;
import com.example.listexampleapi.Services.ListVolleyCallback;
import com.example.listexampleapi.View.ViewUtils.ArticleAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivityPresenter {
    private RequestService requestService;

    public MainActivityPresenter(Context context) {
        this.requestService = RequestService.getInstance(context);
    }

    public void getArticleList(final String article, final ArticleAdapter adapter){
        adapter.clear();

        this.requestService.getSearchArticles(article, new ListVolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) throws JSONException {
                ListArticleParse listArticleParse = new ListArticleParse();

                final List<Article> articles = listArticleParse.parseObject(result);

                for(int i = 0; i < articles.size(); i++){
                    getImage(articles.get(i));
                }

                adapter.setItems(articles);
            }

            @Override
            public void onError(String result) throws Exception {

            }
        });
    }

    private void getImage(final Article article){
        this.requestService.getImage(article ,article.getUrlImage(), new ImageVolleyCallback() {
            @Override
            public void onImageSuccess(Bitmap bitmap) {
                article.setImage(bitmap);
            }

            @Override
            public void onImageFail() {

            }
        });
    }
}
