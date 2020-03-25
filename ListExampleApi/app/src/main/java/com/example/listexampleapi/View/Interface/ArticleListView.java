package com.example.listexampleapi.View.Interface;

import com.example.listexampleapi.Model.Article;

import java.util.List;

public interface ArticleListView {
    void showResult(List<Article> articleList);
    void invalidOperation();
}
