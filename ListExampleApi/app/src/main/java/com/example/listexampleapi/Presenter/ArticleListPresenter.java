package com.example.listexampleapi.Presenter;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.listexampleapi.Model.Article;
import com.example.listexampleapi.Model.ArticleManager;
import com.example.listexampleapi.Model.Interface.ManagerInterface;
import com.example.listexampleapi.Presenter.Interface.ArticleListPresenterInterface;
import com.example.listexampleapi.Service.ImageVolleyCallback;
import com.example.listexampleapi.Service.Parse.ListArticleParse;
import com.example.listexampleapi.Service.RequestService;
import com.example.listexampleapi.Service.JsonVolleyCallback;
import com.example.listexampleapi.View.Interface.ArticleListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ArticleListPresenter implements ArticleListPresenterInterface {
    private RequestService requestService;
    private ListArticleParse listArticleParse;

    private ArticleListView view;
    private ManagerInterface manager;

    public ArticleListPresenter(Context context, ArticleListView view) {
        this.requestService = RequestService.getInstance(context);
        this.listArticleParse = new ListArticleParse();
        this.view = view;
        this.manager = new ArticleManager(this);
    }

    public void getArticleList(final String article){
        this.requestService.getSearchArticles(article, new JsonVolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) throws JSONException {
                final List<Article> articlesList = listArticleParse.parseArticleList(result);

                for(int i = 0; i < articlesList.size(); i++){
                    getImage(articlesList.get(i));
                }

                if(manager != null){
                    manager.modifyArticles(articlesList);
                }
            }

            @Override
            public void onError(String result) throws Exception {

            }
        });
    }

    private void getImage(final Article article){
        this.requestService.getImage(article.getUrlItemImage(), new ImageVolleyCallback() {
            @Override
            public void onImageSuccess(Bitmap bitmap) {
                article.setItemImage(bitmap);
            }

            @Override
            public void onImageFail() {

            }
        });
    }

    @Override
    public void showResult(List<Article> listArticle) {
        if(this.view != null){
            this.view.showResult(listArticle);
        }
    }

    @Override
    public void invalidOperation() {

    }
}
