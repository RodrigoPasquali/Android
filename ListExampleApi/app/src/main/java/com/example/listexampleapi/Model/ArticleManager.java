package com.example.listexampleapi.Model;

import com.example.listexampleapi.Model.Interface.ManagerInterface;
import com.example.listexampleapi.Presenter.ArticleListPresenter;
import com.example.listexampleapi.Presenter.Interface.ArticleListPresenterInterface;

import java.util.List;

public class ArticleManager implements ManagerInterface {
    private List<Article> articleList;
    private ArticleListPresenterInterface presenter;

    public ArticleManager(ArticleListPresenterInterface presenter){
        this.presenter = presenter;
    }

    public ArticleManager(ArticleListPresenter presenter, List<Article> articles){
        this.presenter = presenter;
        this.articleList = articles;
        modifyArticles(this.articleList);
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
        modifyArticles(this.articleList);
    }

    @Override
    public void modifyArticles(List<Article> articlesList) {
        this.presenter.showResult(articlesList);
    }
}
