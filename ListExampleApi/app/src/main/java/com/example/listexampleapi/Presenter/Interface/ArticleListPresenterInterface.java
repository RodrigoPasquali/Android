package com.example.listexampleapi.Presenter.Interface;

import com.example.listexampleapi.Model.Article;

import java.util.List;

public interface ArticleListPresenterInterface {
    void showResult(List<Article> listArticle);
    void invalidOperation();
    void getArticleList(final String article);
}
