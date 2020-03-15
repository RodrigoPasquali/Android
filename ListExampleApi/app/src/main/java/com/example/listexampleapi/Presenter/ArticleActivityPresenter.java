package com.example.listexampleapi.Presenter;

import android.content.Context;

import com.example.listexampleapi.Model.Article;
import com.example.listexampleapi.Service.JsonVolleyCallback;
import com.example.listexampleapi.Service.Parse.ListArticleParse;
import com.example.listexampleapi.Service.RequestService;

import org.json.JSONException;
import org.json.JSONObject;

public class ArticleActivityPresenter {
    private RequestService requestService;
    private ListArticleParse listArticleParse;
    private Context context;

    public ArticleActivityPresenter(Context context) {
        this.context = context;
        this.requestService = RequestService.getInstance(context);
        this.listArticleParse = new ListArticleParse();
    }

    public void getArticleData(final Article article){
        this.requestService.getArticle(article.getId(), new JsonVolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) throws JSONException {
                listArticleParse.parseArticle(result, article);
            }

            @Override
            public void onError(String result) throws Exception {

            }
        });
    }

    private void setArticleData(){
//        this.context.
    }

}
