package com.example.listexampleapi.Presenter;

import android.content.Context;

import com.example.listexampleapi.Model.Article;
import com.example.listexampleapi.Presenter.Interface.ArticlePresenterInterface;
import com.example.listexampleapi.Service.JsonVolleyCallback;
import com.example.listexampleapi.Service.Parse.ListArticleParse;
import com.example.listexampleapi.Service.RequestService;
import com.example.listexampleapi.View.Interface.ArticleView;

import org.json.JSONException;
import org.json.JSONObject;

public class ArticlePresenter implements ArticlePresenterInterface {
    private RequestService requestService;
    private ListArticleParse listArticleParse;
    private Context context;

    private ArticleView view;
    private Article article;

    public ArticlePresenter(Context context, ArticleView view) {
        this.context = context;
        this.requestService = RequestService.getInstance(context);
        this.listArticleParse = new ListArticleParse();
        this.view = view;
//        this.article = new Article();
    }



//    public void getArticleData(final Article pArticle){
//        this.requestService.getArticle(pArticle.getId(), new JsonVolleyCallback() {
//            @Override
//            public void onSuccess(JSONObject result) throws JSONException {
//                listArticleParse.parseArticle(result, pArticle);
//            }
//
//            @Override
//            public void onError(String result) throws Exception {
//
//            }
//        });
//    }


    public void getArticleData(final String idArticle){
        this.requestService.getArticle(idArticle, new JsonVolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) throws JSONException {
//                listArticleParse.parseArticle(result, pArticle);
                article = new Article(idArticle);
                listArticleParse.parseArticle(result, article);

//                article.articleChange();
                showResult(article);
            }

            @Override
            public void onError(String result) throws Exception {

            }
        });
    }





//    private void setArticleData(){
////        this.context.
//    }

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
