package com.example.listexampleapi.Presenter.Interface;

import com.example.listexampleapi.Model.Article;

public interface ArticlePresenterInterface {
    void showResult(Article article);
    void invalidOperation();
    void getArticle(final String articleId);
}
