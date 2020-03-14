package com.example.listexampleapi.Presenter;

import android.content.Context;

import com.example.listexampleapi.Model.Article;
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

                adapter.setItems(articles);
            }

            @Override
            public void onError(String result) throws Exception {

            }
        });
    }

//    private void getImage(final Article article){
//        this.requestService.getImage(article ,article.getUrlImage(), new ImageVolleyCallback() {
//            @Override
//            public void onImageSuccess(Bitmap bitmap) {
//                article.setImage(bitmap);
//                String a = "s";
//            }
//
//            @Override
//            public void onImageFail() {
//                String a = "s";
////                Bitmap bitmap = new Bitmap();
////                article.setImage(R.drawable.error);
//            }
//        });
//    }
}
