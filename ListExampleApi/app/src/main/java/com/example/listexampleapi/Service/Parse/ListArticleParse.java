package com.example.listexampleapi.Service.Parse;

import android.util.Log;

import com.example.listexampleapi.Model.Article;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListArticleParse {
    private static final String TAG = "ListArticleParse";

    public ListArticleParse() {
    }

    public List<Article> parseArticleList(JSONObject jsonObject){
        List<Article> articles = new ArrayList();
        JSONArray jsonArray = null;

        try {
            jsonArray = jsonObject.getJSONArray("results");

            for(int i=0; i<jsonArray.length(); i++){
                try {
                    JSONObject objeto = jsonArray.getJSONObject(i);

                    Article article = new Article(
                            objeto.getString("id"),
                            objeto.getString("title"),
                            objeto.getString("price"),
                            objeto.getString("thumbnail"));

                    articles.add(article);
                } catch (JSONException e) {
                    Log.e(TAG, "Error de parsing: "+ e.getMessage());
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return articles;
    }

    public Article parseArticle(JSONObject jsonObject, Article article) throws JSONException {
        JSONArray jsonArray = null;

        if (jsonObject.has("title")) {
            article.setTitle(jsonObject.getString("title"));
        }

        if (jsonObject.has("price")) {
            article.setPrice(jsonObject.getString("price"));
        }

        if (jsonObject.has("condition")) {
            article.setCondition(jsonObject.getString("condition"));
        }

        if (jsonObject.has("brand")) {
            article.setBrand(jsonObject.getString("brand"));
        }

        if (jsonObject.has("model")) {
            article.setModel(jsonObject.getString("model"));
        }

        if (jsonObject.has("available_quantity")) {
            article.setAvailableQuantity(jsonObject.getString("available_quantity"));
        }

        if (jsonObject.has("sold_quantity")) {
            article.setSoldQuantity(jsonObject.getString("sold_quantity"));
        }

        if (jsonObject.has("permalink")) {
            article.setLinkToPublication(jsonObject.getString("permalink"));
        }

        try {
            jsonArray = jsonObject.getJSONArray("pictures");

//            for(int i = 0; i < jsonArray.length(); i++){
            for(int i = 0; i < 1; i++){
                try {
                    JSONObject objeto = jsonArray.getJSONObject(i);

                    article.setUrlImage(objeto.getString("secure_url"));
                } catch (JSONException e) {
                    Log.e(TAG, "Error de parsing: "+ e.getMessage());
                }
            }

            List<String> urlImages = new ArrayList<>();

            for(int i = 0; i < jsonArray.length(); i++){
                try {
                    JSONObject objeto = jsonArray.getJSONObject(i);

                    urlImages.add(objeto.getString("secure_url"));

//                    article.setUrlImage(objeto.getString("secure_url"));
                } catch (JSONException e) {
                    Log.e(TAG, "Error de parsing: "+ e.getMessage());
                }
            }

            article.setUrlImages(urlImages);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return article;
    }
}